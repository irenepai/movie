$(document).ready(function() {
        
    $("#queryBtn").click(function(e) {
       console.log('clicked');
		
       $.ajax({
           url: 'http://localhost:8081/movie',
           type: 'GET',
           dataType: 'json',
           success: function(data){
            
			console.log(data);
            $("#grid").jqGrid(
			{
               datatyep:'local',
               data: data,
               colModel:[
                 	 {name:'id', label:'編號'},
                 	 {name:'name', label:'電影名稱'},
                  	 {name:'info', label:'簡介'},
                 	 {name:'score', label:'評分'},
               ],
               height:'auto',
               caption:'Movie List',
               rowNum:10,
               pager: '#pager'
              });

			  console.log("success:" + data);
             },
             
			error:function(data){
                console.log(data);
            }
        });
           
      });
});
