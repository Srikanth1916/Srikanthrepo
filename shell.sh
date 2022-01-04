file1=`git tag -l`
if [[ grep '$GITHUB_REF' $file1 ]]
then
   echo "Match does not found and good to proceed with the next steps"
else
   echo "Match found"
exit 1 
fi
