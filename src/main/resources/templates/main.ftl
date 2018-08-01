<#import "parts/common.ftl" as S>

<@S.page>
<div class="form-rov">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input class="form-control " type="text" name="filter" placeholder="Search message"/>
            <button class="btn btn-primary ml-3" type="submit">Search</button>
        </form>
    </div>
</div>
<a class="btn btn-primary m-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Add new message
</a>
<form method="post" id="collapseExample" class="collapse <#if message??>show</#if>">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Input text:</label>
        <div class="col-sm-4">
            <input class="form-control  ${(textError??)?string("is-invalid","")}"
                   value="<#if message??>${message.text}</#if>" type="text" name="text" placeholder="Введите текст"/>
            <#if textError??>
            <div class="invalid-feedback">
                ${textError}
            </div>
            </#if>
        </div>

    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Input tag:</label>
        <div class="col-sm-4">
            <input class="form-control  ${(tagError??)?string("is-invalid","")}" type="text"
                   value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Введите тег"/>
            <#if tagError??>
            <div class="invalid-feedback">
                ${tagError}
            </div>
            </#if>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-primary mt-1 mb-1" type="submit"> Save </button>
</form>
<#include "parts/messageList.ftl"/>
</@S.page>