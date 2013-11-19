window.xhr = new XMLHttpRequest();

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
var prevHTML='';
function annotate(e) {
	
	e.setAttribute("clickablecoverage", "true");
	window.xhr.open('POST', document.location.href + '?thisisaclickableelementdetector', false);
	console.log('xpath: '+ id);
	if(prevHTML != document.documentElement.outerHTML){
	    id=createXPathFromElement(e);
	    window.xhr.send('xpath~~'+id+'~~add~~'+document.documentElement.outerHTML);
	    prevHTML=document.documentElement.outerHTML;
	}
}

function unannotate(e) {
	console.log('removing attribute: '+e.innerhHTML);
	//e.removeAttribute("clickablecoverage");
	window.xhr.open('POST', document.location.href + '?thisisaclickableelementdetector', false);
	id=createXPathFromElement(e);
	console.log('xpath: '+ id);
    window.xhr.send('xpath~~'+id+'~~remove~~'+document.documentElement.outerHTML);
	
}

var observer = new MutationSummary({
	callback : handleChanges,
	queries : [ {
		all : true
	} ]
});

function handleChanges(summaries) {
	console.log("DOM mutated!");
	
	detectClickables();
}

function detectClickables() {
$(document).ready(function() {
	var all = document.getElementsByTagName("*");
	console.log("length of all elems in doc: "+ all.length);
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
});
}


function createXPathFromElement(elm) { 
    var allNodes = document.getElementsByTagName('*'); 
    for (segs = []; elm && elm.nodeType == 1; elm = elm.parentNode) 
    { 
        if (elm.hasAttribute('id')) { 
                var uniqueIdCount = 0; 
                for (var n=0;n < allNodes.length;n++) { 
                    if (allNodes[n].hasAttribute('id') && allNodes[n].id == elm.id) uniqueIdCount++; 
                    if (uniqueIdCount > 1) break; 
                }; 
                if ( uniqueIdCount == 1) { 
                    segs.unshift('id("' + elm.getAttribute('id') + '")'); 
                    return segs.join('/'); 
                } else { 
                    segs.unshift(elm.localName.toLowerCase() + '[@id="' + elm.getAttribute('id') + '"]'); 
                } 
        } else if (elm.hasAttribute('class')) { 
            segs.unshift(elm.localName.toLowerCase() + '[@class="' + elm.getAttribute('class') + '"]'); 
        } else { 
            for (i = 1, sib = elm.previousSibling; sib; sib = sib.previousSibling) { 
                if (sib.localName == elm.localName)  i++; }; 
                segs.unshift(elm.localName.toLowerCase() + '[' + i + ']'); 
        }; 
    }; 
    return segs.length ? '/' + segs.join('/') : null; 
}; 

function lookupElementByXPath(path) { 
    var evaluator = new XPathEvaluator(); 
    var result = evaluator.evaluate(path, document.documentElement, null,XPathResult.FIRST_ORDERED_NODE_TYPE, null); 
    return  result.singleNodeValue; 
} 

