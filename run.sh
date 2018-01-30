echo "\n1 - CRIANDO PROJETO..."
mvn clean install 
echo "\n2 - CRIANDO E EXECUTANDO IMAGENS..."
docker-compose up -d --build
echo "\n3 - ABRIR O NAVEGADOR PADRÃO EM http://localhost:8081/vendas/"
