
IsADRefererSystemRight=true

var searchs	= new Array()
var adid	= ""

try
{
	searchs	= window.location.search.substring(1).split("&")
	
	for (var i=0; i < searchs.length; i++)
	{
		if (searchs[i].substring(0, "SndaADID".length + 1) == "SndaADID=")
		{
			adid	= searchs[i].substring("SndaADID".length + 1)
			break
		}
	}

	document.write("<img src='http://adrs.sdo.com/ADRefererSystem/LogBot.aspx?SndaRefererUrl=" + (document.referrer !== "" ? document.referrer : "(DIRECT)").replace(/&/g, "%26").replace(/\?/g, "%3F") + "&SndaADID=" + adid.replace(/&/g, "%26").replace(/\?/g, "%3F") + "&SndaInSiteUrl=" + document.location.href.replace(/&/g, "%26").replace(/\?/g, "%3F") + "' style='display:none' onload='try{adrsload();}catch(e){}' onerror='try{adrsload();}catch(e){}' />");
}
catch(e)
{	
	try
	{
		document.write("<img src='http://adrs.sdo.com/ADRefererSystem/LogBot.aspx?SndaRefererUrl=(DIRECT)&SndaADID=" + adid.replace(/&/g, "%26").replace(/\?/g, "%3F") + "&SndaInSiteUrl=http://adrs.sdo.com/ADRefererSystem/prereg.html' style='display:none' onload='try{adrsload();}catch(e){}' onerror='try{adrsload();}catch(e){}' />");
	}
	catch(e)
	{
		try{adrsload();}catch(e){}
	}
}
			