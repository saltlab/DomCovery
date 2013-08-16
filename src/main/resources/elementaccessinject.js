var geet = Document.prototype.getElementById
Document.prototype.getElementById= function(id) {
   recordIndirectCoverage(this);
   alert(id);
return geet.call(this, id);
   
}
//getelemtnbytagname, children,parents, byname, $("")
function recordIndirectCoverage(e) {
	e.setAttribute("indirectCoverage", "true");
}
