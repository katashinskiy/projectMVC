<#import "parts/common.ftl" as S>
<@S.page>
<h2 class="text-center">${userChannel.username}</h2>
<div >${type}</div>
<ul class="list-group">
    <#list users as User>
        <li class="list-group-item">
            <a href="/messages/${User.id}">${User.username}</a>
        </li>
    </#list>
</@S.page>