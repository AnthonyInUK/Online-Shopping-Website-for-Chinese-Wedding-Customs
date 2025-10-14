export class Product {
    id: string;
    sku: string;
    name: string;
    description: string;
    unitPrice: number;
    imageUrl: string;
    active: boolean;
    unitsInStock: number;
    dateCreated: Date;
    lastUpdate: Date;
    targetUser: string;  // GROOM, BRIDE, COMMON
    required: boolean;   // Whether the product is required (Note: backend returns 'required', not 'isRequired')
}
