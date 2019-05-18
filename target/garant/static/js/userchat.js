/**
 * 
 */
jQuery(document).ready(function($){
    // auto-refresh task
    setInterval('updateUserChatHistory()', 5000); // that's 30 seconds

    onuploadPage();
    
    // Post Message click handler
    $('#postUserMessage').click(onClickPostUserMessage);

});

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function onuploadPage(){
	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	
}

function onClickPostUserMessage() {
	var text = $('#data').val();
    if ($.trim(text)) {
        postChatMessage(text);
    } else {
        $('#data').val('');
        $('span#flash').attr("class", "error");
        $('span#flash').html('The message is empty');
        $('span#flash').fadeIn(2000)
        $('span#flash').delay(5000).fadeOut(2000);
    }
}

function updateUserChatHistory(){
    //get new content through ajax
    $.ajax({
        type : 'GET',
        url : '/garant/loadUserChatHistoryAJAX.json',
        dataType : 'json',
        success : function(data){
            $("#chat").fadeOut(1000);
            $('#chat').html(data);
            $('#chat').fadeIn(1000);
        },
        error : function(e) {
        }
    });
}

function postChatMessage(text) {
	var data = {"message" : $("#data").val()}
    $.ajax({
        type : 'POST',
        contentType : "application/json; charset=utf-8",
        url : '/garant/postUserMessageAJAX.json',            
        data : JSON.stringify(data),
        dataType: 'json', 
        success : function(response){
            if (response.status == 'SUCCESS') {
                $('#data').val('');
                $('span#flash').attr("class", "success");
                $('span#flash').html('The message was posted successfully');
                $('span#flash').fadeIn(2000);
                $('span#flash').delay(5000).fadeOut(2000);
            }
        },
        error : function(e) {
        }
    }).fail(function(jqXHR) {
        $('span#flash').attr("class", "error");
        $('span#flash').html('Failed to post the message: ' + jqXHR.statusText);
        $('span#flash').fadeIn(2000);
        $('span#flash').delay(5000).fadeOut(2000);
    });
}
