package com.bme.syx.cloud.web;


import com.bme.syx.cloud.service.EmissionSourceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//排放源清单
@RestController
@RequestMapping(value = "esc")
public class EmissionSourceController {



    @Autowired
    private EmissionSourceService eissionSourceService;


    //http://localhost:8080/mig/esc/import?customerId=8
    @GetMapping("import")
    public String eissionSourceImport(String customerId){

        if(!StringUtils.isNotBlank(customerId)){
            customerId = "9999";
        }
       String  result =  eissionSourceService.insertEissionSource(customerId);
       return result;
    }
    /*
    *

select *   from t_emission_source where  customer_id  = 8;

insert into t_emission_source
			 (emission_source_no,emission_source_name,emission_source_id,emission_source_type,
			 customer_id,branch_factory_id,branch_factory,production_line_id,production_line,
			 processes_id,processes,process_section_id,process_section,longitude,latitude,static_production_name,
			 static_production_info,is_close,close_detail,materiel,image_url,organization_type,display_name,
			 is_enable,remark,shed_no,production_describe,government_describe,monitor_describe,isit_airtight)
select emission_source_no,emission_source_name,emission_source_id,emission_source_type,
			 customer_id,branch_factory_id,branch_factory,production_line_id,production_line,
			 processes_id,processes,process_section_id,process_section,longitude,latitude,static_production_name,
			 static_production_info,is_close,close_detail,materiel,image_url,organization_type,display_name,
			 is_enable,remark,shed_no,production_describe,government_describe,monitor_describe,isit_airtight
from t_import_emission_source
where  customer_id  = 38 and import_data = '202105261654';

*
*
     **/
}