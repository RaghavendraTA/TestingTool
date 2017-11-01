/*
 *
 * Recording user actions - A Javascript framework
 * Written by : Raghavendra T.A (A604488)
 * 
 */

// Record Process
var isRecordingCEF = true; // Change it to false
var tempStringCEF = null;
var currentOperation = null,
    previousElement = null;

var optsCEF = {
    CLICK: 0,
    TEXT: 1,
    OPTION: 2,
    COMPARE: 3
};

function getXPath(element) {

    if (element == null || element.tagName == null) return null;

    if (element.id) {
        return `//*[@id=${element.id}]`;
    } else if (element.tagName.toUpperCase() === 'BODY') {
        return '/html/body';
    } else {
        var sameTagSiblings = Array.from(element.parentNode.childNodes)
            .filter(e => e.nodeName === element.nodeName);
        var idx = sameTagSiblings.indexOf(element);

        return getXPath(element.parentNode) + '/' + element.tagName.toLowerCase() +
            (sameTagSiblings.length > 1 ? `[${idx + 1}]` : '');
    }
}

function sendEvent(XPath, eventType, data) {
    // Calculate the next timing
    // Send the event to cefQuery();
    var ret_data = "Recorded~" + JSON.stringify({ path: XPath, eventType: eventType, data: data });
}

function setShadow(element) {
    element.style.boxShadow = "1px 1px 2px black, 0 0 25px yellow, 0 0 5px yellow";
}

document.addEventListener('click', function (event) {

    if (!isRecordingCEF) return;

    var element = event.target;
    var tag_name = element.tagName.toLowerCase();

    // Focus changed, send the operation
    if (currentOperation != null && previousElement != null && currentOperation == optsCEF.TEXT)
        sendEvent(previousElement, currentOperation, tempStringCEF);

    previousElement = getXPath(element);
    if (previousElement == null) return;

    if (tag_name == 'input') {

        setShadow(element);
        var attr = element.getAttribute("type").toLowerCase();

        switch (attr) {
            case 'radio':
            case 'submit':
            case 'checkbox':
                currentOperation = optsCEF.CLICK;
                break;
            default:
                currentOperation = null;
                break;
        }
    } else if (tag_name == 'textarea') {
        setShadow(element);

    } else if (tag_name == 'select') {
        setShadow(element);
        currentOperation = optsCEF.OPTION;
        // Add listener

    } else if (tag_name == 'button') {
        setShadow(element);
        currentOperation = optsCEF.CLICK;
    }

    tempStringCEF = null;

    if (currentOperation != null && currentOperation == optsCEF.CLICK && previousElement != null) {
        sendEvent(previousElement, currentOperation, null);
        previousElement = null;
    }
});

document.addEventListener('keyup', function (event) {

    var element = event.target;
    var tag_name = element.tagName.toLowerCase();

    if (tag_name == 'input') {
        var type = element.getAttribute("type").toLowerCase();
        if (type == 'text' || type == 'password') readTextCEF(element);
    } else if (tag_name == 'textarea') {
        readTextCEF(element);
    }
});

function readTextCEF(element) {
    currentOperation = optsCEF.TEXT;
    tempStringCEF = element.value;
}

function compareTextCEF(textData, element) {

}

// Playback
var isPlaying = true; // Change it false for release version

function getElementByXPath(XPath) {
    return document.evaluate(XPath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
}

function playback(delay) {

    tempStore.forEach(function (object) {

        setTimeout(function () {

            var element = getElementByXPath(object.path);
            switch (object.eventType) {
                case optsCEF.CLICK:
                    element.click();
                    break;
                case optsCEF.TEXT:
                    element.value = object.data;
                    break;
                default:
                    break;
            }

        }, delay);

    }, this);
}