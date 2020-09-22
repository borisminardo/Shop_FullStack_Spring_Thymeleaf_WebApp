$(document).ready(function(){
	$('#userForm').bootstrapValidator({
		feedbackIcons: {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields: {
			nome : {
				container : '#infoNome',
				validators : {
					notEmpty : {
						message : 'Il campo nome non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[a-zA-Z ,.'-]{2,30}$/,
						message : 'Da 2 a 30 caratteri (Solo lettere accettate)'
					}
				}
			},
			cognome : {
				container : '#infoCognome',
				validators : {
					notEmpty : {
						message : 'Il campo cognome non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[a-zA-Z ,.'-]{2,30}$/,
						message : 'Da 2 a 30 caratteri (Solo lettere accettate)'
					}
				}
			},
			indirizzo : {
				container : '#infoIndirizzo',
				validators : {
					notEmpty : {
						message : 'Il campo indirizzo non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[a-zA-Z ,.'-]+[0-9]{1,3}$/,
						message : 'Formato indirizzo (Via|Viale|Piazza e n&deg; civico)'
					}
				}
			},
			cap : {
				container : '#infoCap',
				validators : {
					notEmpty : {
						message : 'Il campo cap non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[0-9]{5}$/,
						message : 'Inserire 5 cifre per il cap'
					}
				}
			},
			nascita : {
				container : '#infoNascita',
				validators : {
					notEmpty : {
						message : 'Il campo data non pu&ograve; essere nullo'
					},
					date : {
						format : 'YYYY/MM/DD',
						message : 'Data non valida. Formato: yyyy/mm/dd'
					}
				}
			},
			username : {
				container : '#infoUsername',
				validators : {
					notEmpty : {
						message : 'Il campo username non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[a-zA-Z0-9]{4,10}$/,
						message : 'Da 4 a 10 caratteri (Lettere e numeri accettati)'
					}
				}
			},
			password : {
				container : '#infoPassword',
				validators : {
					notEmpty : {
						message : 'Il campo password non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^(?=.*[a-z])(?=.*[A-Z])(?=.*[\d])[a-zA-Z0-9]{4,20}$/,
						message : 'Da 4 a 20 caratteri (un carattere maiusc e un numero)'
					}
				}
			},
			mail : {
				container : '#infoMail',
				validators : {
					notEmpty : {
						message : 'Il campo mail non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}$/,
						message : 'Inserire una mail valida'
					}
				}
			}
		}
	});
});

		