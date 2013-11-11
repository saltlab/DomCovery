window.xhr = new XMLHttpRequest();

function enableRewrite() {
var geet = Document.prototype.getElementById
Document.prototype.getElementById= function(id) {
	 //notify client the accesses to this element:
	 var r= geet.call(this,id);
     window.xhr.open('POST', document.location.href + '?thisisafunctiontracingcall', false);
     window.xhr.send('id~~'+id+'~~'+document.documentElement.outerHTML);
     //console.log("dom: "+document);
     console.log("id: "+id +" and r is: "+r); 
     
     if(r)
       return modify(r);
     else
       return r;  
}


var geetName = Document.prototype.getElementsByName
Document.prototype.getElementsByName= function(id) {
    console.log("id: "+id+ "  geet: "+geetName); 
    window.xhr.open('POST', document.location.href + '?thisisafunctiontracingcall', false);
    window.xhr.send('name~~'+id+'~~'+document.documentElement.outerHTML);
    return modifyAll(geetName.call(this, id));
}

var geetTagName = Document.prototype.getElementsByTagName
Document.prototype.getElementsByTagName= function(id) {
    console.log("id: "+id+ "  geet: "+geetTagName); 
    window.xhr.open('POST', document.location.href + '?thisisafunctiontracingcall', false);
    window.xhr.send('tagname~~'+id+'~~'+document.documentElement.outerHTML);
    return modifyAll(geetTagName.call(this, id));
}
var geetCssSelector = Document.prototype.getElementsBySelector
Document.prototype.getElementsBySelector= function(id) {
    console.log("cssSelector: "+id+ "  geet: "+geetCssSelector); 
    window.xhr.open('POST', document.location.href + '?thisisafunctiontracingcall', false);
    window.xhr.send('selector~~'+id+'~~'+document.documentElement.outerHTML);
    return modifyAll(geetCssSelector.call(this, id));
}
var geetClassName = Document.prototype.getElementsByClassName
Document.prototype.getElementsByClassName= function(id) {
    console.log("id: "+id+ "  geetClassName: "+geetClassName); 
     window.xhr.open('POST', document.location.href + '?thisisafunctiontracingcall', false);
    window.xhr.send('classname~~'+id+'~~'+document.documentElement.outerHTML);
    return modifyAll(geetClassName.call(this, id));
}
}

function modify(elem){
   elem.setAttribute('indirectcoverage', 'true');
   console.log('indirect coverage has set for element: '+ elem.outerHTML);
   //alert(elem.outerHTML);
   return elem;
}

function modifyAll(elems){
    for (var i = 0; i < elems.length; i++) {
        elems[i]=modify(elems[i]); 
    }
    return elems;
}
