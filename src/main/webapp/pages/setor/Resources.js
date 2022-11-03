function criarSetor(){
    var nome = document.getElementById("nome").value
	var urlCriar = "http://localhost:8080/crud/rest/setor/criar";
	var requestCriacao = new XMLHttpRequest(); 
	requestCriacao.open("POST", urlCriar);	 
 
	requestCriacao.setRequestHeader('Content-type', 'application/json;charset=UTF-8'); 
 
	requestCriacao.send(JSON.stringify({
	"nome": nome
 
	 }))
	 
	 alert("Setor criado no banco de dados!")
	 window.location.href = 'http://localhost:8080/crud'
}

function listarSetores(){
    const urlListagem = "http://localhost:8080/crud/rest/setor/listar";
    var request = new XMLHttpRequest();	
    request.open("GET", urlListagem, true);
    var bodyTable =  document.getElementById("bodyTable");
    var bodyHTML ='';
    request.onload = () => {
        var resposta = JSON.parse(request.response);
        resposta.forEach(r => {
           bodyHTML += `  <tr>  <td> ${r.idSetor} </td> <td> ${r.nome} </td>  
           <td>  <div style = "display: flex;"> <form> <input type ="hidden" value = ${r.idSetor}> <a href = #">  <button type = "button"  onclick = 'pegarId(${r.idSetor})'>  Alterar     </button> </a> </form>
              <form onsubmit = "excluirSetor(${r.idSetor})"> <input type ="hidden" value = ${r.idSetor}> <button> Excluir </button>  </form> </td>   </td> </div> </tr>`          	
        });
        
        bodyTable.innerHTML = bodyHTML
    }
    request.send();

}

function pegarId(id) {    	
    window.location.href = 'http://localhost:8080/crud/pages/setor/atualizarSetor.html?id=' + id
  
}

function carregarInformacoes(){
    var input = document.getElementById("nomeAtt")
    var urlParams = new URLSearchParams(window.location.search);
    var id = urlParams.get('id');
    if(id != null){
        
    var urlListagem = "http://localhost:8080/crud/rest/setor/buscar/" + id;
	var requestListagem = new XMLHttpRequest();	
	requestListagem.open("GET", urlListagem, true);		
    requestListagem.onload = () =>{
        var res = JSON.parse(requestListagem.response)	
        res.forEach(r => {
            input.value = r.nome
        });     
    }   
    requestListagem.send();
    }
    else{
        return   
}
}
    
function atualizarSetor(){
    var urlParams = new URLSearchParams(window.location.search);
    var id = urlParams.get('id');
    var nome = document.getElementById("nomeAtt").value
    var urlAtualizar = "http://localhost:8080/crud/rest/setor/atualizar/" + id;
	var requestAtualizar = new XMLHttpRequest();	
	requestAtualizar.open("PUT", urlAtualizar);	
    requestAtualizar.setRequestHeader('Content-type', 'application/json;charset=UTF-8'); 
 
	requestAtualizar.send(JSON.stringify({
	"nome": nome
	 }))
    alert("Setor atualizado")
    window.location.href = 'http://localhost:8080/crud'
}

function buscarUmSetor(id){
	var bodyDiv = document.getElementById("informacoesSetor")
	bodyDiv.innerHTML = "";
	var urlBusca = `http://localhost:8080/crud/rest/setor/buscar/${id}`
	var requestBusca = new XMLHttpRequest();
	requestBusca.open("GET" , urlBusca , true)
	requestBusca.onload = () => {
		var resposta = JSON.parse(requestBusca.response);
		if(resposta.length == 0){
			alert("Não existe setor com esse ID")
			var input = document.getElementById("id")
			input.value = ""
			input.focus()
		}
		resposta.forEach(r => {
			bodyDiv.innerHTML += `<h3>  Informações do setor </h3> <li> ID: ${r.idSetor} </li>`
			bodyDiv.innerHTML += `<li> Nome: ${r.nome} </li>`
			
		});
		
	
	console.log(resposta)
	}
	
	requestBusca.send();	
		
}

function excluirSetor(id){
	var confirmDelete = confirm("Deseja apagar o setor do banco de dados?")
	
	if(confirmDelete === true){
		var urlDelete = "http://localhost:8080/crud/rest/setor/excluir/" + id;
		var requestDelete = new XMLHttpRequest();	
		requestDelete.open("DELETE", urlDelete, true);	
		requestDelete.send();   
        alert("Setor excluido")
        
	}
}