Juan Felipe Jaramillo Galeano.

Jhovan Daniel Espinal Zapata.

Daniel Alejandro Burgos Ramírez.

Supermercado Los Lentines 

Problema: El supermercado “Los Lentines” necesita un sistema de facturación que genere facturas en las que se evidencie los datos del cliente, los productos comprados, la sede donde se está realizando la compra y la fecha de facturación.

El sistema debe permitir agregar los datos del cliente, agregar productos, agregar los datos de la sucursal donde se realiza la venta, generar el subtotal de la venta y el total de ésta, teniendo en cuenta un IVA del 20%. Además, puede permitir la corrección de la factura, específicamente la eliminación de un producto que el cliente desista o que el cajero haya ingresado por error, todo esto siempre y cuando no se haya generado la factura. 

Una factura solo puede estar asociada a una sucursal y a un cliente. Una factura solo puede ser generada si tiene al menos un producto, un cliente, una sucursal y el total de la compra. 

Por último, el supermercado maneja un sistema de descuentos y de fidelización, el cual funciona de la siguiente manera:

Por cada $200.000 en la compra del cliente, se realizará un descuento del 3 % sobre el subtotal de la venta, con un tope máximo de 15% de descuento en la compra. 

El supermercado tiene un programa de fidelización en el cual asigna 10 puntos por cada 100.000 generados en el total de la factura. Con esto podrá redimir ciertos premios al mostrar las facturas.

Casos de uso:

CrearFactura -> Comando

registrarDatosCliente -> Comando

registrarProducto -> Comando

Eliminar producto -> Comando (Si la factura está generada, no se puede eliminar el producto de la venta ps).

registrarDatosSucursal -> Comando

calcularDescuento -> comando.

calcularTotal ->  disparado por calcularDescuento.

calcularPuntos -> disparado por calcularTotal

Link Modelo de dominio: https://app.diagrams.net/#G1xfAYePPmZzQHRdeYYzIWg7TGwUdK1Ef-