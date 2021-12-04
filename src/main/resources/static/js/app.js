var agregarConcepto = document.getElementById('agregarInput');
var sacarConcepto = document.getElementById('sacarInput');
var agregarCheque = document.getElementById('agregarInputCheque');
var sacarCheque = document.getElementById('sacarInputCheque');
var cantidadInputs = 1;
var cantidadInputsCheque = 1;
// var inputFecha = document.getElementById('fecha');
// var today = new Date();
// inputFecha.setAttribute("value",today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate());

//AGREGAR INPUTS CONCEPTOS

/*agregarConcepto.onclick = function agregarInput() {
	if (cantidadInputs < 4) {
		sacarConcepto.style.cursor = 'pointer';
		sacarConcepto.style.opacity = 1;
		var input = document.getElementById('container' + (cantidadInputs + 1));
		var inputConcepto = document.getElementById('inputConcepto' + (cantidadInputs + 1));
		inputConcepto.value = 'concepto';
		input.style.display = 'flex';
		cantidadInputs++;
		console.log(cantidadInputs);
		if (cantidadInputs == 4) {
			agregarConcepto.style.cursor = 'auto';
			agregarConcepto.style.opacity = 0.2;
		}
	}
}

sacarConcepto.onclick = function sacarInput() {
	console.log(cantidadInputs);
	if (cantidadInputs > 1) {
		agregarConcepto.style.cursor = "pointer";
		agregarConcepto.style.opacity = 1;
		var input = document.getElementById('container' + (cantidadInputs));
		var inputConcepto = document.getElementById('inputConcepto' + (cantidadInputs));
		var inputImporte = document.getElementById('inputImporte' + (cantidadInputs));
		inputConcepto.value = '';
		inputImporte.value = '';
		input.style.display = "none";
		cantidadInputs--;
		console.log(cantidadInputs);
		if (cantidadInputs == 1) {
			sacarConcepto.style.cursor = "auto";
			sacarConcepto.style.opacity = 0.2;
		}
	}
}

//AGREGAR INPUTS CHEQUE

agregarCheque.onclick = function agregarInputCheque() {
	if (cantidadInputsCheque < 4) {
		sacarCheque.style.cursor = 'pointer';
		sacarCheque.style.opacity = 1;
		var input = document.getElementById('containerCheque' + (cantidadInputsCheque + 1));
		input.style.display = "flex";
		cantidadInputsCheque++;
		console.log(cantidadInputsCheque);
		if (cantidadInputsCheque == 4) {
			agregarCheque.style.cursor = "auto";
			agregarCheque.style.opacity = 0.2;
		}
	}
}

sacarCheque.onclick = function sacarInputCheque() {
	if (cantidadInputsCheque > 1) {
		agregarCheque.style.cursor = "pointer";
		agregarCheque.style.opacity = 1;
		var input = document.getElementById('containerCheque' + (cantidadInputsCheque));
		var chequeNumero = document.getElementById('chequeNumero' + (cantidadInputsCheque));
		var chequeBanco = document.getElementById('chequeBanco' + (cantidadInputsCheque));
		var chequeCUIT = document.getElementById('chequeCUIT' + (cantidadInputsCheque));
		var chequeFecha = document.getElementById('chequeFecha' + (cantidadInputsCheque));
		var chequeImporte = document.getElementById('chequeImporte' + (cantidadInputsCheque));
		chequeNumero.value = '';
		chequeBanco.value = '';
		chequeCUIT.value = '';
		chequeFecha.value = '';
		chequeImporte.value = '';
		input.style.display = "none";
		cantidadInputsCheque--;
		console.log(cantidadInputsCheque);
		if (cantidadInputsCheque == 1) {
			sacarCheque.style.cursor = "auto";
			sacarCheque.style.opacity = 0.2;
		}
	}
}*/

function abrirEmergente() {
	var ventanaEmergente = document.getElementById("difuso");
	ventanaEmergente.style.display = "flex";
}
function cerrarEmergente() {
	var ventanaEmergente = document.getElementById("difuso");
	ventanaEmergente.style.display = "none";
}

var now = new Date();
var dia = now.getDate();
if (dia < 10) {
	dia = "0" + dia;
}
var mes = now.getMonth() + 1;
if (mes < 10) {
	mes = "0" + mes;
}
var today = now.getFullYear() + "-" + mes + "-" + dia;
var desde = document.getElementById("desde");
var hasta = document.getElementById("hasta");
desde.value = today;
hasta.value = today;