document.getElementById('form_confere').addEventListener('submit', function(event) {
  event.preventDefault(); // Impede o envio padrão do formulário

  // Captura os dados do formulário
  var formData = new FormData(this);

  // Envia a requisição POST para a API
  fetch('http://localhost/salvar_dados.php', {
    method: 'POST',
    body: formData
  })
  .then(function(response) {
    // Verifica se a requisição foi bem-sucedida
    if (response.ok) {
      return response.text(); // Retorna os dados da resposta da API (opcional)
    } else {
      throw new Error('Erro na requisição');
    }
  })
  .then(function(data) {
    // Manipula os dados de resposta da API (opcional)
    alert('Dados enviados com sucesso!');
    // Faça algo com os dados, como exibir uma mensagem de sucesso ao usuário
  })
  .catch(function(error) {
    // Manipula erros de requisição
    alert('Erro ao enviar os dados!');
    console.log(error);
    // Faça algo com o erro, como exibir uma mensagem de erro ao usuário
  });
});

