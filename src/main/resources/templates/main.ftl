<#import "parts/common.ftl" as S>
<#import "parts/login.ftl" as L>
<@S.page>
<@L.logout />

<form method="post">
    <input type="text" name="text" placeholder="Введите текст"/>
    <input type="text" name="tag" placeholder="Введите тег"/>
    <input type="hidden" name="_csrf" value = "${_csrf.token}"/>
    <button type="submit"> Отправить</button>
</form>

<span>List masegger</span>

<form method="get" action="/main">
    <input type="text" name="filter" placeholder="filter"/>
    <button type="submit">Фильтровать</button>
</form>
<#list masseges as massege>
    <div>
        <b>${massege.id}}</b>
        <span>${massege.text}</span>
        <i>${massege.tag}</i>
        <strong>${massege.authorName}</strong>
    </div>
<#else>
No massege
</#list>
</@S.page>