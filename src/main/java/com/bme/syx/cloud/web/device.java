//#load_module "modules/ngx_stream_module.so";
//        user  root root;
//        worker_processes  1;
//        worker_rlimit_nofile 65535;
//
//        error_log  /bme/nginx/error.log warn;
//        pid        /var/run/nginx.pid;
//
//
//        events {
//        worker_connections  10240;
//        }worker_processes
//
//        stream{
//        log_format proxy '[$time_local] $remote_addr->$upstream_addr  '
//        '$protocol $status $bytes_sent $bytes_received '
//        '$session_time '
//        '"$upstream_bytes_sent" "$upstream_bytes_received" "$upstream_connect_time"';
//
//        access_log off;
//        open_log_file_cache off;
//
//        upstream gpsgateway {
//        least_conn;
//        #server 172.31.141.5:8591;
//        server 172.31.140.209:8591;
//        #server 172.31.141.12:8591;
//        #server 172.31.141.20:8591;
//
//        }
//
//        upstream oldgattway {
//        #least_conn;
//        server 172.31.141.50:8597;
//        server 172.31.141.17:8597;
//        server 172.31.141.18:8597;
//        #server 172.31.140.229:8597;
//        #server 172.31.140.230:8597;
//        }
//
//        server{
//        listen 8597;
//        proxy_pass oldgattway;
//        }
//
//        server{
//        listen 8591;
//        proxy_pass gpsgateway;
//        }
//
//        upstream dis212 {
//        #hash $proxy_protocol_addr consistent;
//        least_conn;
//        server 172.31.140.228:8092;
//        server 172.31.140.221:8092;
//        server 172.31.235.185:8092;
//        #server 172.31.140.229:8092;
//        #server 172.31.140.229:8093;
//        #server 172.31.140.230:8092;
//        #server 172.31.140.230:8093;
//        }
//
//        server{
//        listen 8092;
//        proxy_pass dis212;
//        }
//
//        server{
//        listen 8094;
//        proxy_pass 172.31.140.230:8094;
//        }
//
//        upstream bme-data-openapi {
//        server 172.31.141.28:9220;
//        }
//        server {
//        listen 9220;
//        proxy_pass bme-data-openapi;
//        }
//
//        #    upstream cloudsocket1 {
//        #    	hash $remote_addr consistent;
//        #        server 172.31.140.206:4000;
//        #    }
//        #    server {
//        #    	listen 8594;
//        #   		proxy_pass cloudsocket1;
//        #   	}
//
//        }
//
//        http {
//        include       /etc/nginx/mime.types;
//        default_type  application/octet-stream;
//
//        log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
//        '$status $body_bytes_sent "$http_referer" '
//        '"$http_user_agent" "$http_x_forwarded_for"';
//
//        access_log  /var/log/nginx/access.log  main;
//
//        sendfile        on;
//        #tcp_nopush     on;
//
//        keepalive_timeout  300;
//
//        gzip  on;
//        gzip_min_length 1K;
//        gzip_comp_level 1;
//        gzip_types text/plain application/json application/javascript application/x-javascript text/css application/xml text/javascript application/x-httpd-php image/jpeg image/gif image/png application/vnd.ms-fontobject font/ttf font/opentype font/x-woff image/svg+xml;
//        gzip_vary on;
//        gzip_disable "MSIE [1-6]\.";
//        gzip_buffers 32 4k;
//        gzip_http_version 1.0;
//
//        #限制同一 IP 地址并发连接数
//        limit_conn_zone $binary_remote_addr zone=concurrent_num:10m;
//        #限制同一 IP 某段时间的访问量
//        limit_req_zone $binary_remote_addr zone=req_num:10m rate=15r/s;
//
//        upstream git{
//        #		server app.bmetech.com:7000;
//        server 172.31.140.215:7000;
//        }
//
//        upstream bme-sso-server {
//        ip_hash;
//        #		server 172.31.140.203:9030 max_fails=1 fail_timeout=10;
//        server 172.31.141.61:9030 max_fails=1 fail_timeout=10;
//        server 172.31.141.60:9030 max_fails=1 fail_timeout=10;
//        server 172.31.141.63:9030 max_fails=1 fail_timeout=10;
//        }
//
//        upstream bme-app {
//        ip_hash;
//        server 172.31.141.45:9050 max_fails=1 fail_timeout=10;
//        server 172.31.141.46:9050 max_fails=1 fail_timeout=10;
//        }
//        upstream bme-mvc {
//        #ip_hash;
//        server 172.31.141.51:9040 max_fails=1 fail_timeout=10;
//        server 172.31.141.52:9040 max_fails=1 fail_timeout=10;
//        }
//
//        upstream screen {
//        #ip_hash;
//        server 172.31.141.27:9070 max_fails=1 fail_timeout=10;
//        server 172.31.141.24:9070 max_fails=1 fail_timeout=10;
//        server 172.31.141.25:9070 max_fails=1 fail_timeout=10;
//        }
//
//        upstream bme-platform {
//        #ip_hash;
//        server 172.31.141.54:9095 max_fails=1 fail_timeout=10;
//        server 172.31.140.205:9095 max_fails=1 fail_timeout=10;
//        server 172.31.141.4:9095 max_fails=1 fail_timeout=10;
//        #server 172.31.141.3:9095 max_fails=1 fail_timeout=10;
//        #server 172.31.141.11:9095 max_fails=1 fail_timeout=10;
//        }
//
//        upstream bme-screen-service {
//        server 172.31.235.65:9070 max_fails=3 fail_timeout=30s;
//        }
//
//        upstream bme-vis-service {
//        server 172.31.235.134:9070 max_fails=3 fail_timeout=30s;
//        server 172.31.235.174:9070 max_fails=3 fail_timeout=30s;
//        }
//
//        upstream bme-pc-service {
//        server 172.31.235.73:9631 max_fails=3 fail_timeout=30s;
//        }
//
//        upstream bme-pad-service {
//        server 172.31.140.229:9666 max_fails=3 fail_timeout=30s;
//        }
//        upstream bme-app-service {
//        server 172.31.235.73:9643 max_fails=3 fail_timeout=30s;
//        }
//
//        upstream bme-vispc-service {
//        server 172.31.235.149:9631 max_fails=3 fail_timeout=30s;
//        server 172.31.235.187:9631 max_fails=3 fail_timeout=30s;
//        }
//
//        upstream bme-visapp-service {
//        server 172.31.235.155:9643 max_fails=3 fail_timeout=30s;
//        server 172.31.235.157:9643 max_fails=3 fail_timeout=30s;
//        }
//
//        upstream bme-openapi-service {
//        server 172.31.235.65:9666 max_fails=3 fail_timeout=30s;
//        }
//
//        upstream bme-visopenapi-service {
//        server 172.31.235.124:9666 max_fails=3 fail_timeout=30s;
//        }
//
//        server {
//        listen 80 default_server;
//        server_name _;
//        return 404;
//        }
//
//        server {
//        listen 443 default_server;
//        server_name _;
//        return 404;
//        }
//
//        server {
//        listen 9091 default_server;
//        server_name _;
//        return 404;
//        }
//        server {
//        listen 9002 default_server;
//        server_name _;
//        return 404;
//        }
//
//        server {
//        listen 443;
//        server_name app.bmetech.com;
//        ssl off;
//        root /root/index.html;
//        index index.html index.htm;
//        #ssl_certificate  /etc/nginx/cert/server.pem;
//        #ssl_certificate_key /etc/nginx/cert/server.key;
//        ssl_certificate  /etc/nginx/cert/START-bmetech-com.crt;
//        ssl_certificate_key /etc/nginx/cert/START-bmetech-com.key;
//        ssl_session_timeout 5m;
//        ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4;
//        #ssl_ciphers ALL:!DH:!EXPORT:!RC4:+HIGH:+MEDIUM:!LOW:!aNULL:!eNULL;
//        ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
//        ssl_prefer_server_ciphers on;
//
//        location /app {
//        proxy_pass http://47.105.203.159:8666;
//        #proxy_pass http://app.bmetech.com:8666;
//        client_max_body_size 20M;
//        }
//
//        location /bme-app {
//        proxy_pass http://bme-app;
//        proxy_set_header Host $host;
//        }
//        location /screen {
//        proxy_pass http://screen;
//        proxy_set_header Host $host;
//        }
//        location /bme-mvc {
//        proxy_pass http://bme-mvc;
//        proxy_set_header Host $host;
//        }
//
//        location /vedio {
//        alias /webapp/upload/;
//        }
//
//        location /map {
//        #	alias /opt/project/html/;
//        proxy_pass http://172.31.140.222;
//        }
//
//
//        location /bme-sso-server {
//        proxy_pass http://bme-sso-server;
//        }
//        location /pc {
//        proxy_pass http://172.31.140.222;
//        }
//
//        location /bme/info {
//        #proxy_pass http://172.31.141.17:9071;
//        proxy_pass http://172.31.141.35:9071;
//        }
//
//        location / {
//        # 这个大小的设置非常重要，如果 git 版本库里面有大文件，设置的太小，文件push 会失败，根据情况调整
//        client_max_body_size 50m;
//        proxy_redirect off;
//        #以下确保 gitlab中项目的 url 是域名而不是 http://git，不可缺少
//        proxy_set_header Host $host;
//        proxy_set_header X-Real-IP $remote_addr;
//        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
//        # 反向代理到 gitlab 内置的 nginx
//        proxy_pass http://git;
//        index index.html index.htm;
//        }
//
//        }
//
//        server {
//        listen 80;
//        server_name app.bmetech.com;
//        rewrite ^(.*)$ https://$host$1 permanent;
//        }
//
//        server {
//        listen 80;
//        server_name wx.bmetech.com;
//
//        location /mp {
//        proxy_pass http://172.31.140.246:8081;
//        }
//        location /platform {
//        #proxy_pass http://172.31.141.54:9095;
//        proxy_pass http://bme-platform;
//
//        }
//
//        location /platform-test {
//        proxy_pass http://172.31.141.39:8566;
//        }
//
//        location /platform-f {
//        proxy_pass http://172.31.141.4:9095;
//        }
//
//        location / {
//        proxy_pass http://172.31.140.222;
//
//        }
//        }
//
//        server {
//        listen 8008;
//        server_name wx.bmetech.com;
//        location / {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://172.31.140.209:8008;
//        }
//        }
//
//        # server {
//        #    listen 80;
//        #   server_name *.wuanhj.com;
//        #
//        #       location / {
//        #          proxy_pass http://172.31.140.208:8090;
//        #
//        #     }
//        #
//        #       location /platform {
//        #          index /wuanhbj/front/platform/index.html;
//        #         alias /wuanhbj/front/platform;
//        #    }
//        #
//        #       location /wuan/wx {
//        #          alias /wuanhbj/front/fuwuhao/;
//        #     }
//        #
//        #       location /services {
//        #          proxy_pass http://172.31.140.205:8092/services;
//        #     }
//        #
//        #   location /management {
//        #      proxy_pass http://172.31.140.208:9081/management;
//        # }
//        # }
//
//        server {
//        listen 9091;
//        server_name wx.bmetech.com;
//
//        proxy_set_header Host $host:$server_port;
//        proxy_set_header X-Real-IP $remote_addr;
//        proxy_set_header REMOTE-HOST $remote_addr;
//        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
//
//        location /client {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://172.31.140.209:8008;
//        }
//
//        location /screen {
//        #限制请求量
//        # limit_req zone=req_num burst=5;
//        # limit_req_status 506;
//        if ($request_method = 'OPTIONS') {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers Content-Type,Access-Token;
//        add_header Access-Control-Allow-Methods POST;
//        return 200;
//        }
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers Content-Type,Access-Token;
//        add_header Access-Control-Allow-Methods GET,POST;
//        #         proxy_pass http://172.31.140.209:9070;
//        proxy_pass http://screen;
//
//        }
//
//        location / {
//        #限制请求量
//        limit_req zone=req_num burst=5;
//        limit_req_status 506;
//
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        #proxy_pass http://172.31.141.54:9095;
//        proxy_pass http://bme-platform;
//        }
//        }
//
//        server {
//        listen 9002;
//        server_name wx.bmetech.com;
//        location / {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://172.31.141.61:9001;
//
//        }
//        }
//
//        upstream nacos {
//        server 172.31.141.11:8848;
//        server 172.31.141.21:8848;
//        server 172.31.141.3:8848;
//        }
//
//        server {
//        listen 8848;
//        server_name 172.31.140.215;
//
//        location / {
//        proxy_pass http://nacos;
//        }
//        }
//
//        server {
//        listen       80;
//        server_name  screen.bmetech.com;
//
//        location / {
//        root   html;
//        index  index.html index.htm;
//        }
//
//        location /screen {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-screen-service;
//        }
//
//        location /standardstage {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-screen-service;
//        }
//
//
//        location /pc {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-pc-service;
//        client_max_body_size 50m;
//        }
//
//        location /pad {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-pad-service;
//        }
//
//        location /GangLu {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        }
//
//        location /bme {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://47.105.172.179:9911;
//        }
//
//        location /operation {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://172.31.140.229:9638;
//        }
//
//        }
//
//        server {
//        listen       443;
//        ssl on;
//        server_name  screen.bmetech.com;
//        ssl_certificate  /etc/nginx/cert/5609409_screen.bmetech.com.pem;
//        ssl_certificate_key /etc/nginx/cert/5609409_screen.bmetech.com.key;
//
//
//        location / {
//        root   html;
//        index  index.html index.htm;
//        }
//
//        location /screen {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-screen-service;
//        }
//
//        location /standardstage {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-screen-service;
//        }
//
//        location /pc {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-pc-service;
//        client_max_body_size 50m;
//        }
//
//        location /pad {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-pad-service;
//        }
//
//
//        location /app {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-app-service;
//        }
//
//        location /GangLu {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        }
//
//        location /operation {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://172.31.140.229:9638;
//        }
//
//        }
//
//        server {
//        listen       80;
//        server_name  vis.bmetech.com;
//
//
//        location / {
//        root   /etc/nginx/html;
//        #root    /bmetech/html;
//        index  index.html index.htm;
//        }
//
//        location ^~ /scada/ {
//        alias /etc/nginx/html/bmetech/;
//        #alias  /bmetech/html/bmetech/;
//        index  index.html index.htm;
//        access_log  /var/log/nginx/scada.log;
//        error_log   /var/log/nginx/scada_error.log;
//        }
//
//        location ^~ /scada/proxy {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        add_header Content-Security-Policy "upgrade-insecure-requests;connect-src *";
//        proxy_pass http://bme-vis-service;
//        }
//
//        location /vis {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        add_header Content-Security-Policy "upgrade-insecure-requests;connect-src *";
//        proxy_pass http://bme-vis-service;
//        }
//        location /visstage {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        add_header Content-Security-Policy "upgrade-insecure-requests;connect-src *";
//        proxy_pass http://bme-vis-service;
//        }
//
//
//        location /vispc {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-vispc-service;
//        client_max_body_size 50m;
//        }
//
//        location /visapp {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-visapp-service;
//        }
//
//        location /bme {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://172.31.141.39:9911;
//        }
//        location /operation {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://172.31.141.46:9638;
//        }
//
//        }
//
//
//
//        server {
//        listen       443;
//        ssl on;
//        server_name  vis.bmetech.com;
//        ssl_certificate  /etc/nginx/cert/5609409_screen.bmetech.com.pem;
//        ssl_certificate_key /etc/nginx/cert/5609409_screen.bmetech.com.key;
//        #ssl_certificate  /etc/nginx/cert/server.crt;
//        #ssl_certificate_key /etc/nginx/cert/server.key;
//        ssl_session_timeout 5m;
//        ssl_ciphers  HIGH:!aNULL:!MD5;
//        ssl_prefer_server_ciphers on;
//
//        location / {
//        root   /etc/nginx/html;
//        index  index.html index.htm;
//        }
//
//        location ^~ /scada/ {
//        alias /etc/nginx/html/bmetech/;
//        #alias  /bmetech/html/bmetech/;
//        index  index.html index.htm;
//        access_log  /var/log/nginx/scada443.log;
//        error_log   /var/log/nginx/scada443_error.log;
//        }
//
//        location /vis {
//        add_header Content-Security-Policy upgrade-insecure-requests;
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        add_header Content-Security-Policy "upgrade-insecure-requests;connect-src *";
//        if ( $request_uri !~* \.mp4 ) {
//
//        rewrite ^(.mp4)$ http://$host$1 permanent;
//        }
//        proxy_pass http://bme-vis-service;
//        }
//        location /visstage {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        add_header Content-Security-Policy "upgrade-insecure-requests;connect-src *";
//        proxy_pass http://bme-vis-service;
//        }
//
//
//        location /vispc {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-vispc-service;
//        client_max_body_size 50m;
//        }
//
//        location /visapp {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://bme-visapp-service;
//        }
//
//        location /operation {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://172.31.141.46:9638;
//        }
//
//        }
//
//        server {
//        listen 80;
//        server_name oa.bmetech.com;
//
//        location / {
//        proxy_pass http://172.31.141.68:80;
//        proxy_redirect   http:// $scheme://;
//        proxy_set_header X-Forwarded-Proto $scheme;
//        proxy_set_header Host $http_host;
//        proxy_set_header X-Real-IP $remote_addr;
//        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
//        }
//        }
//
//        server {
//        listen 443;
//        ssl on;
//        server_name oa.bmetech.com;
//        ssl_certificate  /etc/nginx/cert/5609409_screen.bmetech.com.pem;
//        ssl_certificate_key /etc/nginx/cert/5609409_screen.bmetech.com.key;
//        client_max_body_size 20m;
//
//        location / {
//        proxy_pass http://172.31.141.68:80;
//        proxy_redirect   http:// $scheme://;
//        proxy_set_header X-Forwarded-Proto $scheme;
//        proxy_set_header Host $http_host;
//        proxy_set_header X-Real-IP $remote_addr;
//        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
//        }
//        }
//
//        server {
//        listen 80;
//        server_name oaapp.bmetech.com;
//
//        location / {
//        proxy_pass http://172.31.141.68:8999;
//        proxy_redirect   http:// $scheme://;
//        proxy_set_header X-Forwarded-Proto $scheme;
//        proxy_set_header Host $http_host;
//        proxy_set_header X-Real-IP $remote_addr;
//        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
//        }
//
//        }
//
//
//        server {
//        listen 80;
//        server_name autoemission.bmetech.com;
//
//        location / {
//        add_header Access-Control-Allow-Origin *;
//        add_header Access-Control-Allow-Headers X-Requested-With;
//        add_header Access-Control-Allow-Methods GET,POST;
//        proxy_pass http://172.31.235.103:9999;
//        }
//
//        }
//        server {
//        listen 443;
//        ssl on;
//        server_name oaapp.bmetech.com;
//        ssl_certificate  /etc/nginx/cert/5609409_screen.bmetech.com.pem;
//        ssl_certificate_key /etc/nginx/cert/5609409_screen.bmetech.com.key;
//        client_max_body_size 20m;
//
//        location / {
//        proxy_pass http://172.31.141.68:8999;
//        proxy_redirect   http:// $scheme://;
//        proxy_set_header X-Forwarded-Proto $scheme;
//        proxy_set_header Host $http_host;
//        proxy_set_header X-Real-IP $remote_addr;
//        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
//        }
//        }
//        server {
//        listen       443;
//        ssl on;
//        server_name  openapi.bmetech.com;
//        ssl_certificate  /etc/nginx/cert/5609409_screen.bmetech.com.pem;
//        ssl_certificate_key /etc/nginx/cert/5609409_screen.bmetech.com.key;
//
//        location / {
//        proxy_pass http://bme-openapi-service;
//        }
//        }
//        server {
//        listen       443;
//        ssl on;
//        server_name  visopenapi.bmetech.com;
//        ssl_certificate  /etc/nginx/cert/5609409_screen.bmetech.com.pem;
//        ssl_certificate_key /etc/nginx/cert/5609409_screen.bmetech.com.key;
//
//        location / {
//        proxy_pass http://bme-visopenapi-service;
//        }
//
//        }
//        #	upstream platform {
//        #		server 172.31.141.11:9095;
//        #server 172.31.141.21:9095;
//        #        server 172.31.141.3:9095;
//        #	}
//        #	server {
//        #		listen 9999;
//        #       server_name wx.bmetech.com;
//        #
//        #     proxy_set_header Host $host:$server_port;
//        #    proxy_set_header X-Real-IP $remote_addr;
//        #   proxy_set_header REMOTE-HOST $remote_addr;
//        #  proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
//        #	location / {
//        #		limit_req zone=req_num burst=5;
//        #       limit_req_status 506;
//        #
//        #           add_header Access-Control-Allow-Origin *;
//        #          add_header Access-Control-Allow-Headers X-Requested-With;
//        #         add_header Access-Control-Allow-Methods GET,POST;
//        #		proxy_pass http://platform;
//        #	}
//        #
//        #	}
//        include /etc/nginx/conf.d/*.conf;
//}
