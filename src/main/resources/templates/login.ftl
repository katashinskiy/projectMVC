<#import "parts/common.ftl" as S>
<#import "parts/login.ftl" as L>
<@S.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div class="alert alert-danger" role="alert">
        ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
    </#if>
<@L.login "/login" false/>
</@S.page>