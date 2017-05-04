function sendData(subject) {
	$.ajax({
		type : "POST",
		url : "dataSend",
		datatype : "json",
		data : {
			dataSent : JSON.stringify(subject),
		},
		success : function(data) {
			location.reload();
		},
	});
}

function init() {
	// form default prevention
	$("#left_speaker_form").submit(function(event) {
		event.preventDefault();
	});
	$("#mixer_form").submit(function(event) {
		event.preventDefault();
	});
	$("#right_speaker_form").submit(function(event) {
		event.preventDefault();
	});
	$("#light_form").submit(function(event) {
		event.preventDefault();
	});
	$("#hdd_form").submit(function(event) {
		event.preventDefault();
	});
	$("heather_form").submit(function(event) {
		event.preventDefault();
	});
	$("#oth1_form").submit(function(event) {
		event.preventDefault();
	});
	$("#oth2_form").submit(function(event) {
		event.preventDefault();
	});

	// send data

	$("#left_speaker").on("click", function() {
		sendData("left_speaker");
	});

	$("#mixer").on("click", function() {
		sendData("mixer");
	});

	$("#right_speaker").on("click", function() {
		sendData("right_speaker");
	});
	$("#light").on("click", function() {
		sendData("light");
	});
	$("#hdd").on("click", function() {
		sendData("hdd");
	});
	$("#heather").on("click", function() {
		sendData("heather");
	});
	$("#oth1").on("click", function() {
		sendData("oth1");
	});
	$("#oth2").on("click", function() {
		sendData("oth2");
	});
}

$(document).ready(init);