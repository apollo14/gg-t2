package gg.t1

import model.CalculationsFilter
import model.EBroker
import model.EOperationType
import model.gpw.Operation
import model.gpw.Register

import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.LocalDateTime

class RegisterController {

    def registerService
    def calculationService
    def operationService
    def filter
    def wallet
    def pricesService

    def index() {
        def registers = []
        def incomeTotal = BigDecimal.ZERO;
        wallet.registers.values().each {
            def income = registerService.calculateIncome(it, filter)
            registers.add([value: it, income: income])
            incomeTotal += income
        }
        [registers: registers, incomeTotal: incomeTotal, filter: filter]
    }

    def show(String id){
        def income = registerService.calculateIncome(wallet.registers.get(id), filter)
        def register = [value: wallet.registers.get(id), income: income]
        def calculations = wallet.registers.get(id).calculations

        if (filter){
            calculations = calculationService.filterSold(calculations, filter)
        }

        [register: register, calculations: calculations]
    }

    def loadData(){
        registerService.loadData(wallet)
        redirect(action: "index")
    }

    def saveData(){
        registerService.saveData(wallet)
        redirect(action: "index")
    }

    def addRegister(){

    }

    def saveRegister(String id){
        def register = new Register(id: id)
        registerService.addRegister(register)
        redirect(action: "index")
    }
    def addOperation(String id, String operationType){
        [id: id, operationType: operationType]
    }

    def saveOperation(String id, Date  date, Integer volume, String price, String operationType, String broker){

        LocalDateTime t = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
        def operation = new Operation(registerId: id, dateCreated: t, type: EOperationType.valueOf(operationType), volume: volume, price: new BigDecimal(price), broker: EBroker.valueOf(broker))
        registerService.add(wallet, operation)
        redirect(action: "show", params: [id: id])

    }

    def filter(String fromDate_year, String toDate_year){
        setFilterValues(fromDate_year, toDate_year)
        redirect(action: "index")
    }

    def clearFilter(){
        def date = String.valueOf(Calendar.instance.get(Calendar.YEAR))
        setFilterValues(date, date)
        redirect(action: "index")
    }

    def fullDataSet(){
        def from = "2000"
        def to = String.valueOf(Calendar.instance.get(Calendar.YEAR))
        setFilterValues(from, to)
        redirect(action: "index")
    }
    def private setFilterValues(String from, String to){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy")
        def fromDate = sd.parse(from)
        def toDate = sd.parse(to)
        filter = new CalculationsFilter(fromYear: from, toYear: to, fromDate: fromDate, toDate: toDate)
    }

    def operationsList(String id){
        def register = wallet.registers.get(id)
        [register: register, operations: operationService.filter(register.operations, filter)]
    }

    def pricesList(String id){
        def prices = pricesService.loadPrices(id)
        [prices: prices]
    }
}
