echo "\n1 - CRIANDO PROJETO..."
mvn clean install 
echo "\n2 - CRIANDO E EXECUTANDO IMAGENS..."
docker-compose up
echo "\n3 - ABRINDO NAVEGADOR PADRÃO EM http://localhost:8081/vendas/"
x-www-browser http://localhost:8081/vendas/ 