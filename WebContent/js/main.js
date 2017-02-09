$("#submitregister").click(function(){
	var password = $("#pwdregister").val();
	var mail = $("#mailregister").val();
    $.ajax({
       url : 'register', // La ressource ciblée
       type : 'POST', // Le type de la requête HTTP.
       data : {
         'mail': mail,
          'pwd': password
    }
    });
   
});

$("#submitlogin").click(function(){
	var password = $("#pwdlogin").val();
	var mail = $("#maillogin").val();
    $.ajax({
       url : 'login', // La ressource ciblée
       type : 'POST', // Le type de la requête HTTP.
       data : {
         'mail': mail,
          'password': password
          },
       success : function(code_html, statut){ // code_html contient le HTML renvoyé
    	   location.reload(true);
          }
    });
});

$("#logoutLink").click(function(){
    $.ajax({
       url : 'logout', // La ressource ciblée
       type : 'GET', // Le type de la requête HTTP.
       success : function(code_html, statut){ // code_html contient le HTML renvoyé
    	   location.reload(true);
          }
    });
});