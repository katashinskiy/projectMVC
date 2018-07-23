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
    Add message
</a>
<form method="post" id="collapseExample" class="collapse">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Input text:</label>
        <div class="col-sm-4">
            <input class="form-control " type="text" name="text" placeholder="Введите текст"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Input tag:</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="tag" placeholder="Введите тег"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-primary mt-1 mb-1" type="submit"> Send</button>
</form>

<div class="mt-2">List masegger</div>
<div class="card-columns">
    <#list masseges as massege>
        <div class="card my-3" style="width: 18rem;">
            <div class="m-2">
                <span>${massege.text}</span>
                <i>${massege.tag}</i>
            </div>
            <div class="card-footer text-muted">${massege.authorName}</div>
        </div>
    <#else>
    No message
    </#list>
</div>
</@S.page>