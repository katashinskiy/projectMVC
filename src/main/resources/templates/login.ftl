<#import "parts/common.ftl" as S>
<#import "parts/login.ftl" as L>
<@S.page>
    ${message?if_exists}
<@L.login "/login" false/>
</@S.page>