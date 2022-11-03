function criarFuncionario(){
    //Pegar os valores dos inputs
    pegarSetores();
    var nome = document.getElementById("nome").value
    var dtAniversario = document.getElementById("dtAniversario").value
    var foto = document.getElementById("foto").value   
    var setor = document.getElementById("idSetor").value
    
   

    var urlCriar = "http://localhost:8080/crud/rest/funcionario/criar";
	var requestCriacao = new XMLHttpRequest(); 
	requestCriacao.open("POST", urlCriar);	 
    requestCriacao.setRequestHeader('Content-type', 'application/json');
    requestCriacao.send(JSON.stringify({
        "nome": nome,
        "dtAniversario" : dtAniversario,
        "foto": foto,
        "idSetor" : setor  

    }))
    alert("Funcionario criado no banco de dados!")
    window.location.href = 'http://localhost:8080/crud'
    
}

function listarFuncionarios() {

	const urlListagem = "http://localhost:8080/crud/rest/funcionario/listar";
	var requestListagem = new XMLHttpRequest();	
	requestListagem.open("GET", urlListagem, true);	
	var bodyHTML = ""
	var bodyTable =  document.getElementById("bodyTable");
	requestListagem.onload = () => {
	var resposta = JSON.parse(requestListagem.response);
	
	resposta.forEach(r => {
		bodyHTML += ` <tr>  <td> ${r.id} </td> 
		<td> ${r.nome} </td> 
		<td> ${r.dtAniversario} </td> 
		<td> ${r.foto} </td> 
		<td>${r.setor}  </td> 
		<td> <div style = "display: flex;"> <form> <input type ="hidden" value = ${r.id}> <a href = "#"> <button onclick = 'pegarId(${r.id})' type = "button"> Alterar </button>   </a> </form> 
		 <form onsubmit = "excluirFuncionario(${r.id})"> <input type ="hidden" value = ${r.id}> <button> Excluir </button>  </form> </td>  </tr> </div> ` 
		
	});
	bodyTable.innerHTML = bodyHTML
	console.log(resposta);
	
}
	requestListagem.send();
}
function buscarUmFuncionario(id){
	var bodyDiv = document.getElementById("informacoesFuncionario")
	bodyDiv.innerHTML = "";
	var urlBusca = `http://localhost:8080/crud/rest/funcionario/buscar/${id}`
	var requestBusca = new XMLHttpRequest();
	requestBusca.open("GET" , urlBusca , true)
	requestBusca.onload = () => {
		var resposta = JSON.parse(requestBusca.response);
		if(resposta.length == 0){
			alert("Não existe funcionario com esse ID")
			var input = document.getElementById("id")
			input.value = ""
			input.focus()
		}
		resposta.forEach(r => {
			bodyDiv.innerHTML += `<h3>  Informações do funcionario </h3> <li> ID: ${r.id} </li>`
			bodyDiv.innerHTML += `<li> Nome: ${r.nome} </li>`
			bodyDiv.innerHTML += `<li> Data de aniversario: ${r.dtAniversario} </li>`
			bodyDiv.innerHTML += `<li> Foto: ${r.foto} </li>`
			bodyDiv.innerHTML += `<li> Setor : ${r.setor} </li>`
		});
		
	
	console.log(resposta)
	}
	
	requestBusca.send();	
		
}

function pegarSetores(){
    // Retornar os setores
    const urlListagem = "http://localhost:8080/crud/rest/setor/listar";
    var request = new XMLHttpRequest();	
    request.open("GET", urlListagem, true);
    var bodyTable =  document.getElementById("bodyTable");
    var bodyHTML ='';
    request.onload = () => {
        var resposta = JSON.parse(request.response);
        resposta.forEach(r => {
           bodyHTML += ` <tr>  <td> ${r.idSetor} </td> <td> ${r.nome} </td> </tr>`          	
        });
        
        bodyTable.innerHTML = bodyHTML
    }
    //console.log(resposta)
    request.send();
}
function pegarId(id) {    	
    window.location.href = 'http://localhost:8080/crud/pages/funcionario/atualizarFuncionario.html?id=' + id
  
}

function carregarInformacoes(){
	pegarSetores();
    var urlParams = new URLSearchParams(window.location.search);
    var id = urlParams.get('id');
    if(id != null){
        
    var urlListagem = "http://localhost:8080/crud/rest/funcionario/buscar/" + id;
	var requestListagem = new XMLHttpRequest();	
	requestListagem.open("GET", urlListagem, true);		
    requestListagem.onload = () =>{
        var res = JSON.parse(requestListagem.response)	
        res.forEach(r => {
            document.getElementById("nomeAtt").value = r.nome
			document.getElementById("dtAniversarioatt").value = r.dtAniversario
			document.getElementById("fotoAtt").value = r.foto
			document.getElementById("idSetorAtt").value = r.idSetor
        });     
    }   
    requestListagem.send();
    }
    else{
        return   
}
}
    
function atualizarFuncionario(){
	
    var urlParams = new URLSearchParams(window.location.search);
    var id = urlParams.get('id');
    var nome = document.getElementById("nomeAtt").value
	var dtAniversario = document.getElementById("dtAniversarioatt").value
    var foto = document.getElementById("fotoAtt").value   
    var setor = document.getElementById("idSetorAtt").value

    var urlAtualizar = "http://localhost:8080/crud/rest/funcionario/atualizar/" + id;
	var requestAtualizar = new XMLHttpRequest();	
	requestAtualizar.open("PUT", urlAtualizar);	
    requestAtualizar.setRequestHeader('Content-type', 'application/json;charset=UTF-8'); 
 
	requestAtualizar.send(JSON.stringify({
	 	"nome": nome,
        "dtAniversario" : dtAniversario,
        "foto": foto,
        "idSetor" : setor
	 }))
    alert("Funcionario atualizado")
    window.location.href = 'http://localhost:8080/crud'
	
}

function excluirFuncionario(id){
	var confirmDelete = confirm("Deseja apagar o funcionario do banco de dados?")
	
	if(confirmDelete === true){
		const urlDelete = "http://localhost:8080/crud/rest/funcionario/excluir/" + id;
		var requestDelete = new XMLHttpRequest();	
		requestDelete.open("DELETE", urlDelete, true);	
		requestDelete.send();   
		alert("Funcionario excluido")
	}
	
}
