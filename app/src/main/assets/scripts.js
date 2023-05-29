    window.scrollTo(0, 0);

function validaRG(rg) {
    var rgFormatado = rg.replace(/[^0-9]/g, ''); // Remove caracteres que não são números
    if (rgFormatado.length != 9) {
        return false;
    }
    var digitos = rgFormatado.split('');
    var soma = (digitos[0] * 2) + (digitos[1] * 3) + (digitos[2] * 4) + (digitos[3] * 5) + (digitos[4] * 6) + (digitos[5] * 7) + (digitos[6] * 8) + (digitos[7] * 9);
    var resto = soma % 11;
    var digitoVerificador = 0;
    if (resto != 0) {
        digitoVerificador = 11 - resto;
    }
    if (digitoVerificador != digitos[8]) {
        return false;
    }
    return true;
}
function validaRG(rg) {
    var rgFormatado = rg.replace(/[^0-9]/g, ''); // Remove caracteres que não são números
    if (rgFormatado.length != 9) {
        return false;
    }
    var digitos = rgFormatado.split('');
    var soma = (digitos[0] * 2) + (digitos[1] * 3) + (digitos[2] * 4) + (digitos[3] * 5) + (digitos[4] * 6) + (digitos[5] * 7) + (digitos[6] * 8) + (digitos[7] * 9);
    var resto = soma % 11;
    var digitoVerificador = 0;
    if (resto != 0) {
        digitoVerificador = 11 - resto;
    }
    if (digitoVerificador != digitos[8]) {
        return false;
    }
    return true;
}
//função para validar a data de nascimento
function validarDataNascimento(dataNascimento) {
//verificar se a data de nascimento está no formato correto (DD/MM/AAAA)
if (!/\d{2}\/\d{2}\/\d{4}/.test(dataNascimento)) {
    return false;
}

//obter os valores do dia, mês e ano da data de nascimento
var partesData = dataNascimento.split('/');
var dia = parseInt(partesData[0], 10);
var mes = parseInt(partesData[1], 10);
var ano = parseInt(partesData[2], 10);

//verificar se a data é válida
var data = new Date(ano, mes - 1, dia);
if (data.getFullYear() !== ano || data.getMonth() + 1 !== mes || data.getDate() !== dia) {
    return false;
}

//verificar se o usuário tem pelo menos 18 anos de idade
var idade = new Date().getFullYear() - ano;
if (idade < 18) {
    return false;
}

//se chegou até aqui, a data de nascimento é válida
return true;
}


function validateField(field) {
    if (field.checkValidity()) {
        field.classList.remove("is-invalid");
    } else {
        field.classList.add("is-invalid");
    }
}
function addRow() {
  var table = document.querySelector("table tbody");
  var newRow = table.insertRow();
  var cols = "";

  cols += '<td><input type="text" class="form-control" name="nome[]" required></td>';
  cols += '<td><input type="text" class="form-control" name="parentesco[]" required></td>';
  cols += '<td><input type="tel" class="form-control" name="idade[]" required></td>';
  cols += '<td><input type="text" class="form-control" name="profissao[]" required></td>';
  cols += '<td><select class="form-select" name="possui_renda[]"><option value="1">Sim</option><option selected value="0">Não</option></select></td>';
  cols += '<td><select class="form-select" name="pcd[]"><option value="1">Sim</option><option selected value="0">Não</option></select></td>';
  cols += '<td><select class="form-select" name="recebe_aposentadoria[]"><option value="1">Sim</option><option selected value="0">Não</option></select></td>';
  cols += '<td><select class="form-select" name="recebe_auxilio[]"><option value="1">Sim</option><option selected value="0">Não</option></select></td>';
  cols += '<td><button type="button" class="btn btn-danger btn-sm" onclick="removeRow(this)">X</button></td>';


  newRow.innerHTML = cols;
}
function removeRow(btn) {
  var row = btn.parentNode.parentNode;
  row.parentNode.removeChild(row);
}
function saveRow(btn) {
  var row = btn.parentNode.parentNode;
  row.parentNode.removeChild(row);
}
function addCol() {
    const container = document.createElement('div');
    container.className = 'row d-sm-block d-md-none animate__animated animate__fadeIn';
    container.innerHTML = `
    <div class="alert alert-secondary">
    <div class="col-md-2">
      <div class="form-floating mb-3">
          <input type="text" class="form-control" required id="floatingInput" onkeyup="handleOnlyLetters(event); validateField(this); " name="nome_integrante[]" placeholder="Nome">
          <label for="floatingInput">Nome</label>
          <div class="invalid-feedback">Por favor, preencha este campo.</div>
      </div>
    </div>
    <div class="col-md-2">
      <div class="form-floating mb-3">
          <input type="text" class="form-control" required id="floatingInput" onkeyup="handleOnlyLetters(event); validateField(this); " name="parentesco_integrante[]" placeholder="Parentesco">
          <label for="floatingInput">Parentesco</label>
          <div class="invalid-feedback">Por favor, preencha este campo.</div>
      </div>
    </div>
    <div class="col-md-2">
      <div class="form-floating mb-3">
        <input type="tel" class="form-control" onkeyup="handleOnlyNumbers(event); validateField(this);" name="idade[]" placeholder="Idade" required>
        <label for="floatingInput">Idade</label>
          <div class="invalid-feedback">Por favor, preencha este campo.</div>
      </div>
    </div>
    <div class="col-md-2">
      <div class="form-floating mb-3">
        <input type="text" class="form-control" onkeyup="handleOnlyLetters(event); validateField(this);" name="profissao[]" placeholder="Profissão" required>
        <label for="floatingInput">Profissão</label>
          <div class="invalid-feedback">Por favor, preencha este campo.</div>
      </div>
    </div>
    <div class="col-md-2">
      <div class=" form-floating mb-3">
        <select class="form-select" required id="renda[]" >
            <option selected value="0"></option>
            <option value="1">Sim</option>
            <option value="2">Não</option>
        </select>
        <label for="aposentado">Possui renda?</label>
        <div class="invalid-feedback">Por favor, preencha este campo com um valor válido.</div>
      </div>
    </div>
    <div class="col-md-2">
      <div class=" form-floating mb-3">
        <select class="form-select" required id="pcd[]" >
            <option selected value="0"></option>
            <option value="1">Sim</option>
            <option value="2">Não</option>
        </select>

        <label for="aposentado">É Aposentado?</label>
                <div class="invalid-feedback">Por favor, preencha este campo com um valor válido.</div>
            </div>
            </div>
            <div class="col-md-2">
            <div class=" form-floating mb-3">
        <select class="form-select" required id="pcd[]" >
            <option selected value="0"></option>
            <option value="1">Sim</option>
            <option value="2">Não</option>
        </select>

       <label for="aposentado">Recebe Auxílio Brasil?</label>
                <div class="invalid-feedback">Por favor, preencha este campo com um valor válido.</div>
            </div>
            </div>
            <div class="col-md-2">
            <div class=" form-floating mb-3">
        <select class="form-select" required id="pcd[]" >
            <option selected value="0"></option>
            <option value="1">Sim</option>
            <option value="2">Não</option>
        </select>

        <label for="aposentado">PcD?</label>
        <br>
        <div class="invalid-feedback">Por favor, preencha este campo com um valor válido.</div>
      </div>
    </div>
    <div class="container text-center">
      <div class="row">
          <div class="col align-self-start"></div>
          <div class="col align-self-center"></div>
          <div class="col align-self-end">
            <button type="button" class="btn btn-info " style="color: white;" onclick="addCol()">+</button>
            <button type="button" class="btn btn-danger " style="color: white;" onclick="removeCol()">X</button>
                          
            <br><br>
          </div>
      </div>
    </div>
    <hr>
</div>
</div>`;
    
  const addButton = document.querySelector('.container.text-center button');
  addButton.parentElement.parentElement.parentElement.insertBefore(container, addButton.parentElement.parentElement.nextSibling);
}
function removeCol() {
  const rows = document.querySelectorAll('.row.d-sm-block.d-md-none');
  const lastRow = rows[rows.length - 1];
  setTimeout(() => {
    lastRow.remove();
  }, 100);
}




  


function mascaraCPF(i){
    var v = i.value;

    if(isNaN(v[v.length-1])){
        i.value = v.substring (0, v.length-1);
        return;
    }
    i.setAttribute("maxlength","14");
    if (v.length == 3 || v.length == 7) i.value += ".";
    if (v.length == 11) i.value += "-";
}

function mascaraRG(i){
    var v = i.value;

    if(isNaN(v[v.length-1])){
        i.value = v.substring (0, v.length-1);
        return;
    }
    i.setAttribute("maxlength","12");
    if (v.length == 2 || v.length == 6) i.value += ".";
    if (v.length == 10) i.value += "-";
}

function mascaraDataNasc(i){
    var v = i.value;

    if(isNaN(v[v.length-1])){
        i.value = v.substring (0, v.length-1);
        return;
    }
    i.setAttribute("maxlength","10");
    if (v.length == 2 || v.length == 5) i.value += "/";
}

const handlePhone = (event) => {
  let input = event.target
  input.value = phoneMask(input.value)
}
const phoneMask = (value) => {
  if (!value) return ""
  value = value.replace(/\D/g,'')
  value = value.replace(/(\d{2})(\d)/,"($1) $2")
  value = value.replace(/(\d)(\d{4})$/,"$1-$2")
  return value
}

const handleData = (event) => {
  let input = event.target
  input.value = maskDate(input.value)
}
const maskDate = value => {
  return value
    .replace(/\D/g, "")
    .replace(/(\d{2})(\d)/, "$1/$2")
    .replace(/(\d{2})(\d)/, "$1/$2")
    .replace(/(\d{4})(\d)/, "$1")
}

const handleOnlyLetters = (event) => {
  let input = event.target
  input.value = maskOnlyLetters(input.value)
}
const maskOnlyLetters = value => {
  return value.replace(/[0-9!@#¨$%^&*)(+=._-]+/g, "")
}

const handleOnlyNumbers = (event) => {
  let input = event.target
  input.value = maskOnlyNumbers(input.value)
}
const maskOnlyNumbers = value => {
  return value.replace(/\D/g, "")
}
const handleCEP = (event) => {
  let input = event.target
  input.value = maskCEP(input.value)
}
const maskCEP = value => {
  return value.replace(/\D/g, "").replace(/^(\d{5})(\d{3})+?$/, "$1-$2")
}

function onSelectAposentado() {
  var select = document.getElementById("aposentado");
  var pessoaRecebeuInput = document.getElementById("pessoaRecebeu");
  var valorAposentadoriaInput = document.getElementById("valorAposentadoria");

  if (select.value == "1") {
    pessoaRecebeuInput.disabled = false;
    valorAposentadoriaInput.disabled = false;
  } else {
    pessoaRecebeuInput.disabled = true;
    valorAposentadoriaInput.disabled = true;
    pessoaRecebeuInput.value = "";
    valorAposentadoriaInput.value = "";
  }
}
function onSelectAuxilio() {
  var select = document.getElementById("auxilio");
  var pessoaAuxilioInput = document.getElementById("pessoaAuxilio");
  var valorAuxilioInput = document.getElementById("valorAuxilio");

  if (select.value == "1") {
    pessoaAuxilioInput.disabled = false;
    valorAuxilioInput.disabled = false;
  } else {
    pessoaAuxilioInput.disabled = true;
    valorAuxilioInput.disabled = true;
    pessoaAuxilioInput.value = "";
    valorAuxilioInput.value = "";
  }
}
function onSelectMoradia() {
    var select = document.getElementById("moradia");
    var moradiaAlugadaInput = document.getElementById("moradiaAlugada");
    var moradiaOutrosInput = document.getElementById("moradiaOutros");

    if (select.value == "1") {
        moradiaAlugadaInput.disabled = true;
        moradiaOutrosInput.disabled = true;
    } else if (select.value == "2") {
        moradiaAlugadaInput.disabled = false;
        moradiaOutrosInput.disabled = true;
        moradiaOutrosInput.value = '';
    } else if (select.value == "3") {
        moradiaAlugadaInput.disabled = true;
        moradiaAlugadaInput.value = '';
        moradiaOutrosInput.disabled = false;
    } else {
        moradiaAlugadaInput.disabled = true;
        moradiaOutrosInput.disabled = true;
        moradiaAlugadaInput.value = '';
        moradiaOutrosInput.value = '';
    }
}
