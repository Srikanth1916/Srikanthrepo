#!/bin/sh

i=1
while IFS= read -r line; do
    declare lhh$((i++))="$line"
done <repo.txt


count="$((i-1))"
for ((j=1; j<=count; j++)); do
   pro=lhh$j
sed -e "s/\${project.artifactId}/${!pro}/g" deploy.yaml
kubectl apply -f deploy.yaml
done
