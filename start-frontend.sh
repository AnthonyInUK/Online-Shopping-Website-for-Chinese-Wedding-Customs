#!/bin/bash

echo "🚀 Starting Wedding Shop Frontend..."
echo ""

cd frontend/angular-ecommerce

echo "📦 Setting Node.js environment variables..."
export NODE_OPTIONS=--openssl-legacy-provider

echo "🔧 Starting Angular development server..."
npx ng serve --port 4200 --host 0.0.0.0

echo ""
echo "✅ Frontend service started successfully!"
echo "🌐 Access at: http://localhost:4200"