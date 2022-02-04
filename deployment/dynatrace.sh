namespace=`kubectl get ns | awk '{print $1}'| grep -i dynatrace`

if [ "$namespace" = "dynatrace" ]
then
    echo "dynatrace namespace, oneagent, activegate, custom resource is already installed"
else
	  echo "Installation of active gate"
	  wget $ACTIVEGATE_URL -O install.sh && sh ./install.sh --api-url "$DYNATRACE_URL" --api-token "$API_TOKEN" --paas-token "$PASS_TOKEN" --cluster-name "$EKS_CLUSTERNAME"
fi
