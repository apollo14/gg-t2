
<g:form name="filter" action="filter" class="form-inline">
    <div class="row">
        <div class="form-group col-sm-3">
            <label for="fromDate" >From</label>
            <g:datePicker name="fromDate" value="${filter?.fromDate}" precision="year" years="${Calendar.instance.get(Calendar.YEAR)..2000}"></g:datePicker>
            <label for="toDate">To</label>
            <g:datePicker name="toDate" value="${filter?.toDate}" precision="year" years="${Calendar.instance.get(Calendar.YEAR)..2000}"></g:datePicker>
        </div>
        <div class="form-group col-sm-1">
            <g:submitButton name="Filter" class="btn" style="width: 100%"/>
        </div>
        <div class="form-group col-sm-1">
            <g:link controller="register" action="clearFilter">
                <input type="button" value="Clear" class="btn" style="width: 100%"/>
            </g:link>
        </div>
        <div class="form-group col-sm-1">
            <g:link controller="register" action="fullDataSet">
                <input type="button" value="Full" class="btn" style="width: 100%"/>
            </g:link>
        </div>
        <div class="col-sm-3"></div>
    </div>
</g:form>


