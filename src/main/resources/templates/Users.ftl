<#import "parts/common.ftl" as S>
<@S.page>
All users :

<table>
<#list users as U>

<tr>
    <td>${U.userName}</td>
    <td><#list U.role as role>${role}<#sep >, </#list></td>
    <td><a href = "/Users/${U.id}"> edit</a> </td>

</tr>

</#list>
</table>
</@S.page>