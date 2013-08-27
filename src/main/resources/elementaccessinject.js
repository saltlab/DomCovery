var geet = Document.prototype.getElementById
Document.prototype.getElementById= function(id) {
   return modified(geet.call(this, id));
}

function modified(elem){
   recordIndirectCoverage(elem);
   alert(elem);
   return elem;
}
function recordIndirectCoverage(e) {
	e.setAttribute("indirectCoverage", "true");
}
