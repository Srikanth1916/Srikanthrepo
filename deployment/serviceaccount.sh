serviceaccount=`kubectl get serviceaccounts | awk '{print $1}'| grep -i skyhive`

if [ "$serviceaccount" = "skyhive" ]

then
        echo "skyhive serviceaccount  exist"
else
         kubectl apply -f service-account.yaml
fi
