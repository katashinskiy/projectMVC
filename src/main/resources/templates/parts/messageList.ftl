<#include "security.ftl"/>
<div class="mt-2">Message List</div>
<div class="card-columns">
    <#list messages as M>
        <div class="card my-3" style="width: 18rem;">
            <div class="m-2">
                <span>${M.text}</span><br/>
                <i>#${M.tag}</i>
            </div>
            <div class="card-footer text-muted input-group">
                <a href="/messages/${M.author.id}"> ${M.authorName} </a>
                <#if M.author.id == UserId || isAdmin>
                <a class="btn btn-primary ml-5" href="/messages/${M.author.id}?message=${M.id}">Edit</a>
                </#if>
                <#if isAdmin>
                 <form action="/main/delete" method="get" class="ml-5">
                    <button type="submit" class="btn btn-outline-danger " style="border: 0px" >—</button>
                     <input type="hidden" name="messageId" value="${M.id}"/>
                 </form>
                </#if>
            </div>

        </div>
    <#else>
    No message
    </#list>
</div>