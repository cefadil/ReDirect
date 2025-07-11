## Funcionamento
- Para inserir um registro, basta enviar uma requisição POST para `localhost:8080/api` com os dados da URL que deseja encurtar, bem como o período em dias que o link deve permanecer disponível. Ver exemplo abaixo:

>     {
>     	"url":"www.google.com",
>     	"validity":10
>     }



- Para obter os dados do registro inserido, basta acessar, via GET 	`localhost:8080/api/{shortcode}`. 
Caso o registro exista, e esteja dentro do período de validade, serão retornados os dados do mesmo. 
Exemplo: `  http://localhost:8080/api/4ATyoM2n8w ` 


- Uma listagem de todos os registros pode ser obtida em `  http://localhost:8080/api/redirects `  , apenas para fins de adminstração/testes. 
	
