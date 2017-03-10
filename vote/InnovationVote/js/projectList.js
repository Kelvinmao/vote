var totalPages=12;
/*$.ajax({
	url: "",
	type: "",
	dataType: "JSON",
	async: false,
	success: function(data) {
		window.totalPages=Math.ceil(data/pageSize);
	}
	error: function(data) {
		alert("error");
	}
})*/

var options = {
	bootstrapMajorVersion: 3,
	alignment: 'center',
    currentPage: 1,
    totalPages: totalPages,
    pageUrl: function(type,page,current) {
    	return ;
    },
    /*onPageClicked: function(event,originalEvent,type,page) {
    	originalEvent.preventDefault();
    	originalEvent.stopPropagation();

    	$.ajax({
    		url: originalEvent.target.href;
    		type: "GET",
    		dataType: "json",
    		success: function(data) {

    		}
    	})
    },*/
    itemTexts:function(type,page,current) {
    	switch(type) {
    		case "first":
    			return "第一页";
    		case "prev":
    			return "上一页";
    		case "next":
    			return "下一页";
    		case "last":
    			return "最后一页";
    		case "page":
    			return page;
    	}
     }
}

$(document).ready(function(){
	$('#project-pagination').bootstrapPaginator(options);
	var h = window.location.href;
	var h_length = h.length - 1;
	var h = h.charAt(h_length);
	$("#list-sidebar").find("a")[h].addClass("list-sidebar-active");
	
});




