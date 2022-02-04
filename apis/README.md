### Services

Each individual deployable service needed to be placed under this folder Example: Service name is `testing`

Each service is an independent maven project which follows the same structure

1) `src/main/java` Java source code for the artifact.
2) `src/main/resources` configuration files and others such as i18n files, per-environment configuration files, and XML
   configurations.
3) `src/test/java` Java source code for tests
4) `src/test/resources` configuration files and others used by tests

pom.xml
---
module should be added under profiles section

```xml
<profile>
    <id>testing</id>
    <modules>
        <module>testing</module>
    </modules>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
</profile>
```

This allows each service to be built separately using profile id.

We need to refer the parent in each service

```xml
<parent>
    <groupId>io.skyhive.veeneer</groupId>
    <artifactId>services</artifactId>
    <version>${revision}</version>
 </parent>
```

Deployment
---
To deploy on to kubernetes need to create a deploy.yml file under deploy folder

**deploy/deploy.yaml**

```yml
# Service
apiVersion: v1
kind: Service
metadata:
  name: testing
  labels:
    app: testing
    service: testing
spec:
  ports:
    - port: 8080
      name: http
  selector:
    app: testing
---
# Deployment
apiVersion: apps/v1
kind: Deployment
metadata:
  name: worker
  labels:
    app: testing
    version: v1
spec:
  replicas: 1
  selector:
    matchLabels:
      app: testing
      version: v1
  template:
    metadata:
      labels:
        app: testing
        version: v1
    spec:
      serviceAccountName: skyhive
      containers:
        - name: worker
          image: ecr_repo/testing
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
          securityContext:
            runAsUser: 1000
---
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: testing
spec:
  ingressClassName: kong
  rules:
    - http:
        paths:
          - path: /testing/
            pathType: Prefix
            backend:
              service:
                name: testing
                port:
                  number: 8080
```