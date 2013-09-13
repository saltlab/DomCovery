var rel = Node.prototype.removeEventListener;
Node.prototype.removeEventListener = function() {
	rel.apply(this, arguments);
	unannotate(this);
}

var ael = Node.prototype.addEventListener;
Node.prototype.addEventListener = function() {
	ael.apply(this, arguments);
	annotate(this);
}

function annotate(e) {
	e.setAttribute("data-cj-clickable", "true");
}

function unannotate(e) {
	e.removeAttribute("data-cj-clickable");
}

var observer = new MutationSummary({
	callback : handleChanges,
	queries : [ {
		all : true
	} ]
});

function handleChanges(summaries) {
	// alert("DOM mutated!");
	detectClickables();
}

function detectClickables() {
	var all = document.body.getElementsByTagName("*");
	for ( var i = 0; i < all.length; i++) {
		var node = all[i];
		if (all[i].onclick != null) {
			annotate(node);
		} else {
			if (node.nodeType == 1) {// element of type html-object/tag
				if (node.tagName == "A" || node.tagName == "BUTTON") {
					annotate(node);
				}
				if (node.tagName == "INPUT"
						&& node.getAttribute("type").toUpperCase() == "SUBMIT") {
					annotate(node);
				}
			}
		}
	}
}








var geet = Document.prototype.getElementById
Document.prototype.getElementById= function(id) {
     console.log("id: "+id+ geet.outerHTML); 
     return modify(geet.call(this, id));
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

var elements=document.body.getElementsByTagName('*');
    //alert(elements.length);
    for( i=0; i < elements.length; i++ ){
       if( isVisible(elements[i])){     
	     console.log("visible element: "+ elements[i].tagName);	
             elements[i].setAttribute("element-visible","true");
       }
    }  
function isVisible(obj)
{
    if (obj == document) return true
    
    if (!obj) return false
    if (!obj.parentNode) return false
    if (obj.style) {
        if (obj.style.display == 'none') return false
        if (obj.style.visibility == 'hidden') return false
    }
    
    //Try the computed style in a standard way
    if (window.getComputedStyle) {
        var style = window.getComputedStyle(obj, "")
        if (style.display == 'none') return false
        if (style.visibility == 'hidden') return false
    }
    
    //Or get the computed style using IE's silly proprietary way
    var style = obj.currentStyle
    if (style) {
        if (style['display'] == 'none') return false
        if (style['visibility'] == 'hidden') return false
    }
    
    return isVisible(obj.parentNode)
}
