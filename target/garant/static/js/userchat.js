/**
 * 
 */
jQuery(document).ready(function($){
    onuploadPage();
    updateUserChatHistory();
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
	var text = $('#dataUserChat').val();
    if ($.trim(text)) {
        postChatMessage(text);
    } else {
        $('#dataUserChat').val('');
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
	var dataUserChat = {"message" : $("#dataUserChat").val()}
    $.ajax({
        type : 'POST',
        contentType : "application/json; charset=utf-8",
        url : '/garant/postUserMessageAJAX.json',            
        data : JSON.stringify(dataUserChat),
        dataType: 'json', 
        success : function(response){
            if (response.status == 'SUCCESS') {
                $('#dataUserChat').val('');
                $('#flash').attr("class", "success");
                $('#flash').html(dataUserChat);
                
            }
            updateUserChatHistory();
        },
        error : function(e) {
        }
    }).fail(function(jqXHR) {
        $('#flash').attr("class", "error");
        $('#flash').html('Failed to post the message: ' + jqXHR.statusText);
        $('#flash').fadeIn(2000);
        $('#flash').delay(5000).fadeOut(2000);
    });
	
	
}
