### Simple instruction

- install graalvm:
```bash
sdk install java 21.0.2-graalce
```

- package
```bash
 mvn clean package -Pnative -X -DskipTests 
```

- checkout `/target`