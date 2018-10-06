        function linkfunc(){
        var from = document.getElementById("from").value;
        var to = document.getElementById("to").value;
        var x = document.getElementById("dd").value;
        var url = "http://localhost:8585/BankAPI/rest/client/report/clientname/"+x+"/from/"+from+"/to/"+to;
        sessionStorage.Url = url;
        }
        
        function addOptions(){
        	var details = fetch('http://localhost:8585/BankAPI/rest/client/all-clients')
        	    .then(function(response){
        	        return response.json();
        	    })

        	    .then(function(data){
        	        let html  = ' ';
        	        var select = document.getElementById('dd');
        	        var option;
        	            for(var i=0; i<data.length;i++){
							option = document.createElement('option');
							option.value = data[i]["client_name"];
							option.text = data[i]["client_name"];
							select.add(option);
            	            }
        				
        	    })
        	    .catch(function(error){
        	        console.log(error)
        	    })
        	}
        