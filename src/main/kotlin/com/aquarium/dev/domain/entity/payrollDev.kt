package payroll

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
internal class Employee {
    @Id
    @GeneratedValue
    var id: Long? = null
    var name: String? = null
    var role: String? = null

    constructor() {}
    constructor(name: String?, role: String?) {
        this.name = name
        this.role = role
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Employee) return false
        val employee = o
        return (id == employee.id && name == employee.name
                && role == employee.role)
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, role)
    }

    override fun toString(): String {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", role='" + role + '\'' + '}'
    }
}