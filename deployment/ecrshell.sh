i=1
while IFS= read -r line; do
    declare lhh$((i++))="$line"
done <repo.txt


count="$((i-1))"
for ((j=1; j<=count; j++)); do
   pro=lhh$j
C=`grep ${!pro} ecr.txt`

if [ -z  "$C" ]
then
echo "creating the repoistory for ${!pro}"
aws ecr create-repository --repository-name ${!pro} --image-scanning-configuration scanOnPush=false --region $AWS_REGION
else
echo "Match found"
fi
done
