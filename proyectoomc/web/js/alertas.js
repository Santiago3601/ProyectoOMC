  function enter(){
console.log("Entro 1");
            var necia = $("#correo").val();
console.log("Entro 2"+necia);          
 if(necia==""||necia==null){
console.log("Entro 2.1");
            swal("Oh no","Hello","warning");
            return;
            }else{
console.log("Entro 2.2");
            swal("Oh no","necia","success");
            return;
            }
            }