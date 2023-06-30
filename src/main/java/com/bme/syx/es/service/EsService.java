package com.bme.syx.es.service;

import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class EsService {

    /**
     * 批量保存数据到ES
     * @param jestClient client
     * @param index index
     * @param list list
     * @param <T> return
     */
    public <T> void batchSave(JestClient jestClient, String index, List<T> list) {
        try {
            if (list!=null && CollectionUtils.isEmpty(list)) {
                return;
            }
            Bulk.Builder bulk = new Bulk.Builder();
            for (T t : list) {
                bulk.addAction(new Index.Builder(t).index(index).type("_doc").refresh(true).build());
            }
            BulkResult result = jestClient.execute(bulk.build());
            log.info("批量入库ES结果:{}", result);
        } catch (Exception e) {
            log.error("批量保存数据异常", e);
        }
    }

    /**
     * query :
     * bool:
     * filter:
     * term
     * <p>
     * term
     * <p>
     * ....
     */
    public <T> T get(JestClient jestClient, Map<String, Object> searchParams, String index, Class<T> clazz) {
        SearchResult result = query(jestClient, searchParams, index, null, null, null, null, null, null, null);
        if (Objects.isNull(result)) {
            return null;
        }
        return result.getSourceAsObject(clazz, true);
    }

    /**
     * 分页查询
     */
    public <T> List<T> getList(JestClient jestClient,
                               Map<String, Object> searchParams,
                               String index,
                               Integer from,
                               Integer size,
                               String sortField,
                               String sort,
                               String rangeField,
                               String gte,
                               String lte,
                               Class<T> clazz) {
        SearchResult result = query(jestClient, searchParams, index, from, size, sortField, sort, rangeField, gte, lte);
        if (Objects.isNull(result)) {
            return null;
        }
        return result.getSourceAsObjectList(clazz, true);
    }

    private SearchResult query(JestClient jestClient,
                               Map<String, Object> searchParams,
                               String index,
                               Integer from,
                               Integer size,
                               String sortField,
                               String sort,
                               String rangeField,
                               String gte,
                               String lte) {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder bool = QueryBuilders.boolQuery();

            //设置条件
            for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
                bool.filter(QueryBuilders.termQuery(entry.getKey(), entry.getValue()));
            }
            searchSourceBuilder.query(bool);

            // 设置范围
            if (StringUtils.isNotBlank(rangeField) && StringUtils.isNotBlank(gte) && StringUtils.isNotBlank(lte)) {
                bool.filter(QueryBuilders.rangeQuery(rangeField).gte(gte).lte(lte));
            }

            //设置分页
            if (Objects.nonNull(from)) {
                searchSourceBuilder.from(from);
            }
            if (Objects.nonNull(size)) {
                searchSourceBuilder.size(size);
            }

            //设置排序
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(sortField)) {
                if (StringUtils.equals("ASC", sort)) {
                    searchSourceBuilder.sort(new FieldSortBuilder(sortField).order(SortOrder.ASC));
                } else {
                    searchSourceBuilder.sort(new FieldSortBuilder(sortField).order(SortOrder.DESC));
                }
            }

            log.info("===> query:{}", searchSourceBuilder.toString());
            //查询
            Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(index).addType("_doc").build();
            return jestClient.execute(search);
        } catch (Exception e) {
            log.error("查询es异常", e);
            return null;
        }
    }
}
