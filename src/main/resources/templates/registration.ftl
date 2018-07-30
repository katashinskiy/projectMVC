<#import "parts/common.ftl" as S>
<#import "parts/login.ftl" as L>
<@S.page >
<div class="text-center mb-5"> Add new user : </div>
${message?if_exists}
<@L.login "/registration" true/>
</@S.page>

