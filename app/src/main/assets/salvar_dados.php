<?php
// Obtém os dados do formulário
$nome_responsavel = $_POST['nome_responsavel'];
$rg_responsavel = $_POST['rg_responsavel'];
$cpf_responsavel = $_POST['cpf_responsavel'];
$nascimento_responsavel = $_POST['nascimento_responsavel'];
$fone_responsavel = $_POST['fone_responsavel'];
$profissao_responsavel = $_POST['profissao_responsavel'];
$rua_moradia = $_POST['rua_moradia'];
$numero_moradia	 = $_POST['numero_moradia	'];
$cidade_moradia = $_POST['cidade_moradia'];
$cep_moradia = $_POST['cep_moradia'];
$situacao_moradia = $_POST['situacao_moradia'];
$alguel_moradia = $_POST['alguel_moradia'];
$descricao_moradia = $_POST['descricao_moradia'];
$nome_integrante = $_POST['nome_integrante'];
$parentesco_integrante = $_POST['parentesco_integrante'];
$idade_integrante = $_POST['idade_integrante'];
$profissao_integrante = $_POST['profissao_integrante'];
$pcd_integrante = $_POST['pcd_integrante'];
$renda_integrante = $_POST['renda_integrante'];
$aposentado_integrante = $_POST['aposentado_integrante'];
$aposentadovalor__integrante = $_POST['aposentadovalor__integrante'];
$auxilio_integrante = $_POST['auxilio_integrante'];
$auxiliovalor_integrante = $_POST['auxiliovalor_integrante'];



// Conecta ao banco de dados
$servername = 'seu_endereco_do_servidor';
$username = 'usuario';
$password = 'senha';
$dbname = 'nome_do_banco';

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die('Falha na conexão com o banco de dados: ' . $conn->connect_error);
}

// Insere os dados no banco de dados
$sql = "INSERT INTO tabela (campo1, campo2) VALUES ('$campo1', '$campo2')";
if ($conn->query($sql) === TRUE) {
    echo 'Dados salvos com sucesso!';
} else {
    echo 'Erro ao salvar os dados: ' . $conn->error;
}

$conn->close();
?>
