namespace=`kubectl get ns | awk '{print $1}'| grep -i istio-system`

if [ "$namespace" = "istio-system" ]

then
        echo "istio-system namespace exist"
else
  echo "configuring the istio"
  mkdir istio
  cd istio
  wget https://github.com/istio/istio/releases/download/1.11.2/istio-1.11.2-linux-amd64.tar.gz
  tar -xvf istio-1.11.2-linux-amd64.tar.gz
  sleep 5
  cd istio-1.11.2
  export PATH=$PATH:$PWD/bin
  istioctl version
  istioctl install -y
  sleep 10
  istioctl verify-install
  echo "configuration of istio is completed"
fi
