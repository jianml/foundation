#!/bin/bash

service="foundation"
image="jianml/foundation:1.0"

# 校验服务是否存在
result=$(docker ps -a --format {{.Names}} | grep -E ^${service}$)
[[ $result == ${service} ]] && docker rm -f ${service} && echo "删除服务[${service}]成功:)"

# 校验镜像是否存在
result=$(docker images ${image} --format {{.Repository}}:{{.Tag}})
[[ ${result} == ${image} ]] && docker rmi ${image} && echo "删除镜像[${image}]成功:)"

docker run \
--restart=always \
--name ${service} \
-v /docker/${service}/log:/home/app/log
-p 9000:8080 \
-d ${image}
