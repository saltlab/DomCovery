var geet = Document.prototype.getElementById
Document.prototype.getElementById= function(id) {
     var r= geet.call(this,id);
     console.log("id: "+id +" and r is: "+r); 
     if(r)
       return modify(r);
     else
       return r;  
}


var geetName = Document.prototype.getElementsByName
Document.prototype.getElementsByName= function(id) {
    console.log("id: "+id+ "  geet: "+geetName); 
    return modifyAll(geetName.call(this, id));
}

var geetTagName = Document.prototype.getElementsByTagName
Document.prototype.getElementsByTagName= function(id) {
    console.log("id: "+id+ "  geet: "+geetTagName); 
    return modifyAll(geetTagName.call(this, id));
}
var geetClassName = Document.prototype.getElementsByClassName
Document.prototype.getElementsByClassName= function(id) {
    console.log("id: "+id+ "  geetClassName: "+geetClassName); 
    return modifyAll(geetClassName.call(this, id));
}
/* add querySelector, child, paretn,...
*/

function modify(elem){
   elem.setAttribute("indirectCoverage", "true");
   console.log("indirect coverage has set for element: "+ elem.outerHTML);
   //alert(elem.outerHTML);
   return elem;
}

function modifyAll(elems){
    for (var i = 0; i < elems.length; i++) {
        elems[i]=modify(elems[i]); 
    }
    return elems;
}
