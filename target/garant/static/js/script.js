/**
 * 
 */
jQuery(document).ready(function($){
    // auto-refresh task
    setInterval('updateChatHistory()', 5000); // that's 30 seconds

    onupload();
    
    // Post Message click handler
    $('#postMessage').click(onClickPostMessage);

    // MessagesPerPage radio button change handler
    $('input:radio[name="messagesPerPage"]').change(setMessagesPerPage);
});

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function onupload(){
	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	
}

function onClickPostMessage() {
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

function updateChatHistory(){
    //get new content through ajax
    $.ajax({
        type : 'GET',
        url : '/garant/loadChatHistoryAJAX.json',
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
        url : '/garant/postMessageAJAX.json',            
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

function setMessagesPerPage(){
    $.ajax({
        type : 'POST',
        url : '/garant/setMessagesPerPagePropAJAX.json',
        dataType : 'json',
        data : {
            limit : $(this).val()
        },
        success : function(data){
        },
        error : function(e) {
        }
    });
    // and now update chat history
    updateChatHistory();
}