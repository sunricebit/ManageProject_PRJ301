create database ProjectManagement
use ProjectManagement

create table project(
	proID char(4) primary key,
	projectName nvarchar(30),
	start Date,
	deadline Date,
	customer nvarchar(15),
	check(start < deadline),
);

create table task(
	taskID nvarchar(15) primary key,
	detail nvarchar(15),
	username nchar(15),
	proID char(4)
	foreign key (username) references employee(username),
);

create table employee(
	username nchar(15) primary key,
	fullname nvarchar(30),
	email nvarchar(30),
	pass nvarchar(500),
	proID char(4),		--id project
	NRole bit,	-- chức vụ
	avatar nvarchar(500),
	foreign key (proID) references project(proID)
	on delete set null
	on update cascade,
);

create table noti(
	notiID char(6) primary key,
	username nchar(15),
	detail nvarchar(200),
	foreign key (username) references employee(username)
)
--drop table task
--drop database ProjectManagement
/*
insert into project(proID, projectName,start, deadline, customer)
values
('01', 'Du An Khu Do Thi','2021-07-20', '2022-03-30', 'A'),
('02', 'Du An Khu Villa','2021-12-01', '2022-04-30', 'B'),
('03', 'Du An Giai Tri','2021-12-20', '2022-05-30', 'C'),
('04', 'Du An Dau Tu','2022-01-20', '2022-06-30', 'D');

insert into typeEmployee(typeEmployee, insurrance)
values
('Full-time', '300'),
('Part-time', '100'),
('Collaborator', '50');

insert into employee(ID, name, birth, phone, sex, address,	typeEmployee, salary, deptsID, proID, position)
values
('01','Pham Minh A','1998-04-05','0123091238','Male','Ha Noi','Full-time','2000','KT','01','Truong Phong'),
('02','Tran Thi B','1997-03-07','0297430912','Female','Ninh Binh','Part-time','1500','KD','02','Truong Phong'),
('03','Phan Van C','1994-09-12','0123131478','Male','Thai Binh','Full-time','2000','IT','01','Truong Phong'),
('04','Vu Duc D','2000-01-16','0354834932','Male','Quang Ninh','Part-time','500','HC','03','Nhan Vien'),
('05','Nguyen Quoc E','1997-06-09','0376875673','Male','Ha Noi','Collaborator','1500','IT','04','Nhan Vien'),
('06','Pham Van F','1995-02-03','0234828344','Male','Bac Giang','Full-time','1500','NS','02','Truong Phong'),
('07','Nguyen Thi G','1992-02-26','0237486872','Female','Ha Noi','Full-time','3000','MKT','03','Truong Phong'),
('08','Le Quynh H','1994-03-06','0236482467','Female','Thai Nguyen','Collaborator','3000','MKT','04','Nhan Vien'),
('09','Nguyen Duc I','1999-07-17','0495875494','Male','Ho Chi Minh','Part-time','2000','KT','01','Nhan Vien'),
('10','Nguyen Thi J','2000-08-11','0916275315','Female','Thanh Hoa','Part-time','1500','NS','03','Nhan Vien'),
('11','Pham Ngoc K','1999-01-31','0827635716','Female','Ha Noi','Full-time','3000','IT','02','Nhan Vien'),
('12','Dinh Khac L','1980-12-24','0923768484','Male','Hoa Binh','Full-time','4000','MKT','04','Nhan Vien'),
('13','Nguyen Thao M','1993-10-29','0823654762','Female','Phu Tho','Part-time','2500','HC','03','Nhan Vien'),
('14','Luong Duy N','1991-11-18','0378623783','Male','Thai Nguyen','Part-time','2000','IT','02','Nhan Vien'),
('15','Le Thi O','1991-09-16','0349862386','Female','Ha Noi','Full-time','3000','HC','04','Truong Phong'),
('16','Nguyen Trung P','1998-02-02','0923647826','Male','Hai Phong','Full-time','1500','KT','01','Nhan Vien'),
('17','Nguyen Thi Q','1997-05-24','0237482342','Female','Ha Noi','Full-time','1500','KT','03','Nhan Vien'),
('18','Ha Van R','1996-01-12','0382648236','Male','Nam Dinh','Full-time','1500','KD','02','Nhan Vien'),
('19','Nguyen Hoang S','2002-05-22','0924387434','Male','Thanh Hoa','Collaborator','500','MKT','02','Nhan Vien');

---tính tổng số nhân viên
select count(ID) as [Total number of employees]
from employee

---tính độ tuổi tb của nhân viên
select AVG(a.age) as [Average age of all employee]
from
(select year(GETDATE())-YEAR(birth) as age
from employee) as a

---tính tổng tiền lương của nhân viên
select sum(salary) as [Total salary]
from employee

---tính tiền lương trung bình của cả công ty
select AVG(salary) as [Average salary of all employee]
from employee

---Lấy tổng số tiền bảo hiểm của nv
select a.ID, b.insurrance
from employee a, typeEmployee b

---Lấy ra số lương của từng nhân viên
select ID, name, salary
from employee

---Lấy ra số tiền bảo hiểm của từng nhân viên
select a.ID, a.name, b.insurrance
from employee a, typeEmployee b
where a.typeEmployee = b.typeEmployee

---Trong số các nhân viên lương > 1000 tìm người nhiều tuổi nhất
select a.*
from employee a,
(select year(GETDATE())-YEAR(birth) as age, ID
from employee) as b
where a.salary > 1000 and a.ID = b.ID and b.age = (
	select max(age)
	from 
	(select year(GETDATE())-YEAR(birth) as age
	from employee) as a
) 

---Tìm các nhân viên sinh năm 1997
select * from employee
where YEAR(birth) = '1997'

---Tìm địa chỉ của các phòng ban
select departmentName, address
from department

---Tìm khách hàng của các project
select projectName, customer
from project

---procedure: tính tổng số nhân viên trong phòng ban
create procedure TotalEmployee(@DepartmentName nvarchar(30), @TotalEmployee int output)
as 
begin
	select @TotalEmployee =  count(a.name)
	from employee a, department b
	where a.deptsID = b.deptsID and b.departmentName = @DepartmentName
end;
drop proc TotalEmployee
declare @t int
exec TotalEmployee'Phong Ke Toan', @t output
print @t

---procedure: Tìm những project có deadline trước ngày nào đó 
create procedure ProjectHasDeadlineBeforeDate(@Deadline date)
as 
begin
	select projectName
	from project
	where @Deadline > deadline
end;
drop proc ProjectHasDeadlineBeforeDate

exec ProjectHasDeadlineBeforeDate '2022-05-30'


---procedure: lấy các nhân viên của mỗi loại Part-time - Full-time - Collaborator
create procedure EmployeeOfEachType(@TypeEmployee nvarchar(15))
as 
begin
	select *
	from employee
	where @TypeEmployee = typeEmployee 
end;

drop proc EmployeeOfEachDate

exec EmployeeOfEachType 'Part-time'


---procedure: lấy các nhân viên có cùng quê
create procedure EmployeeFromTheSameHometown(@ID char(6))
as 
begin
	select distinct a.name, b.name 
	from employee a, employee b
	where a.ID != b.ID and a.address = b.address and a.ID=@ID
end;

drop proc EmployeeFromTheSameHometown

exec EmployeeFromTheSameHometown '01'

---procedure: tìm tên nhân viên bắt đầu bằng 1 ký tự 
create procedure FindEmployeeByFirstLetter(@BeginName char(1))
as 
begin
	select *
	from employee
	where name like @BeginName + '%' 
end;

drop proc FindEmployeeByFirstLetter

exec FindEmployeeByFirstLetter 'P'

---procedure: tính tuổi tb của một phòng ban
create procedure AverageAgeOfADepartment(@DepartmentName nvarchar(30))
as 
begin
	select AVG(a.age) as [Average age]
	from
	(select year(GETDATE())-YEAR(birth) as age
	from employee a, department b
	where a.deptsID = b.deptsID and b.departmentName = @DepartmentName
	) as a
end;

drop proc AverageAgeOfADepartment

exec AverageAgeOfADepartment 'Phong Ke Toan'

---procedure: tìm project có những nhân viên nào làm
create procedure FindEmployeeInProject(@ProjectName nvarchar(30))
as 
begin
	select * 
	from employee a, project b
	where a.proID = b.proID and b.projectName = @ProjectName
end;

drop proc FindEmployeeInProject

exec FindEmployeeInProject 'Du An Khu Villa'

---procedure: tìm project có sự tham gia của các phòng ban nào
create procedure FindDepartmentInProject(@ProjectName nvarchar(30))
as 
begin
	select distinct c.departmentName
	from employee a, project b, department c
	where a.proID = b.proID and b.projectName = @ProjectName and a.deptsID = c.deptsID
end;

drop proc FindDepartmentInProject

exec FindDepartmentInProject 'Du An Khu Villa'

---trigger: tạo nv mới in ra các phòng ban và project có sự thay đổi
create trigger AddNewEmployee on employee after insert
as
begin
	declare @DeptsID char(4)
	declare @ProID char(4)
	declare @ProjectName nvarchar(30)
	declare @DepartmentName nvarchar(30)
	select @ProID  = inserted.proID, @DeptsID = inserted.deptsID
	from inserted
	select @ProjectName = projectName
	from project
	where proID = @ProID
	select @DepartmentName = departmentName
	from department 
	where deptsID = @DeptsID 
	print 'Departments: ' + @DepartmentName + ' and Projects: ' + @ProjectName + ' have new employee'
end;
drop trigger AddNewEmployee

insert into employee(ID, name, birth, phone, sex, address,	typeEmployee, salary, deptsID, proID, position)
values
('20','Pham Minh B','1998-04-05','0123091238','Male','Ha Noi','Full-time','2000','KT','01','Nhan Vien')

delete from employee
where ID = '20'

---trigger: xóa nv cũ in ra các phòng ban và project có sự thay đổi
create trigger DeleteEmployee on employee after delete
as
begin
	declare @DeptsID char(4)
	declare @ProID char(4)
	declare @ProjectName nvarchar(30)
	declare @DepartmentName nvarchar(30)
	select @ProID  = deleted.proID, @DeptsID = deleted.deptsID
	from deleted
	select @ProjectName = projectName
	from project
	where proID = @ProID
	select @DepartmentName = departmentName
	from department 
	where deptsID = @DeptsID 
	print 'Departments: ' + @DepartmentName + ' and Projects: ' + @ProjectName + ' have an employee who has moved'
end;
drop trigger DeleteEmployee

insert into employee(ID, name, birth, phone, sex, address,	typeEmployee, salary, deptsID, proID, position)
values
('20','Pham Minh B','1998-04-05','0123091238','Male','Ha Noi','Full-time','2000','KT','01','Nhan Vien')

delete from employee
where ID = '20'

---trigger: in ra thông tin nhân viên khi update
create trigger UpdateEmployee on employee after update
as
begin
	select * from inserted
end;
drop trigger UpdateEmployee

update employee
set name = 'Pham Minh D'
where ID = '20'

---trigger: in ra thông tin phòng ban khi update
create trigger UpdateDepartment on department after update
as
begin
	select * from inserted
end;
drop trigger UpdateDepartment

update department
set departmentName = 'Ke Toan'
where deptsID = 'KT'

---trigger: in ra thông tin loại nhân viên khi update
create trigger UpdateTypeEmployee on typeEmployee after update
as
begin
	select * from inserted
end;
drop trigger UpdateTypeEmployee

update typeEmployee
set insurrance = '400'
where typeEmployee = 'Full-time'

---trigger: in ra thông tin project khi update
create trigger UpdateProject on project after update
as
begin
	select * from inserted
end;
drop trigger UpdateProject

update project
set projectName = 'Du An Khu Vui Choi'
where proID = '01'

*/