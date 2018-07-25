<#import "parts/common.ftl" as S>
<@S.page>
All users :

<table class="table table-striped table-dark">
    <thead >
    <tr>
        <td scope="col">#</td>
        <td scope="col">User name</td>
        <td scope="col"> User role</td>
        <td scope="col"> Edit</td>

    </tr>
    </thead>
<#list users as U>

<tr>
    <td scope="row">${U.id}</td>
    <td>${U.userName}</td>
    <td><#list U.role as role>${role}<#sep >, </#list></td>
    <td><a href="/Users/${U.id}"> edit</a></td>

</tr>

</#list>

</table>
</@S.page>