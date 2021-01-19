
function validate()
{
    var name = document.getElementById("title").value;
    
    
    if(name===""){
        alert("Title is required");
        console.log("hii");
    }
    else if(name.length<2 || name.length>65)
        alert("Title should have 2 to 65 characters");
    

    
    

}